package io.yang.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.yang.shop.bean.Order;
import io.yang.shop.bean.OrderItem;
import io.yang.shop.bean.Product;
import io.yang.shop.bean.User;
import io.yang.shop.order.mapper.OrderItemMapper;
import io.yang.shop.order.mapper.OrderMapper;
import io.yang.shop.order.service.OrderService;
import io.yang.shop.params.OrderParams;
import io.yang.shop.utils.constants.HttpCode;
import io.yang.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * @author zhangyang
 * @date 2022/07/25 22:41
 * @description 自定义负载均衡
 **/
@Slf4j
@Service("orderServiceV3")
public class OrderServiceV3Impl implements OrderService {

    private String userServer = "server-user";
    private String productServer = "server-product";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public void saveOrder(OrderParams orderParams) {
        String userUrl = this.getServiceUrl(userServer);
        String productUrl = this.getServiceUrl(productServer);

        if (orderParams.isEmpty()) {
            throw new RuntimeException("参数异常： " + JSONObject.toJSONString(orderParams));
        }
        User user = restTemplate.getForObject("http://" + userUrl + "/user/get/" + orderParams.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("未获取到用户信息： " + JSONObject.toJSONString(orderParams));
        }

        Product product = restTemplate.getForObject("http://" + productUrl + "/product/get/" + orderParams.getUserId(), Product.class);
        if (product == null) {
            throw new RuntimeException("未获取到商品信息： " + JSONObject.toJSONString(orderParams));
        }

        if (product.getProStock() < orderParams.getCount()) {
            throw new RuntimeException("商品库存不足： " + JSONObject.toJSONString(orderParams));
        }

        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result result = restTemplate.getForObject("http://" + productUrl + "/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(),
                Result.class);
        assert result != null;
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");

    }

    private String getServiceUrl(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        int index = new Random().nextInt(instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        log.info("负载均衡后的服务地址为:{}", url);
        return url;
    }
}
