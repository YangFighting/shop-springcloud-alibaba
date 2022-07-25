package io.yang.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.yang.shop.bean.Order;
import io.yang.shop.bean.OrderItem;
import io.yang.shop.bean.Product;
import io.yang.shop.bean.User;
import io.yang.shop.order.fegin.ProductService;
import io.yang.shop.order.fegin.UserService;
import io.yang.shop.order.mapper.OrderItemMapper;
import io.yang.shop.order.mapper.OrderMapper;
import io.yang.shop.order.service.OrderService;
import io.yang.shop.params.OrderParams;
import io.yang.shop.utils.constants.HttpCode;
import io.yang.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author zhangyang
 * @date 2022/07/25 22:41
 * @description 使用Ribbon实现负载均衡
 **/
@Slf4j
@Service("orderServiceV5")
public class OrderServiceV5Impl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public void saveOrder(OrderParams orderParams) {


        if (orderParams.isEmpty()) {
            throw new RuntimeException("参数异常： " + JSONObject.toJSONString(orderParams));
        }
        User user =userService.getUser(orderParams.getUserId());
        if (user == null) {
            throw new RuntimeException("未获取到用户信息： " + JSONObject.toJSONString(orderParams));
        }

        Product product = productService.getProduct(orderParams.getProductId());
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

        Result result = productService.updateCount(orderParams.getProductId(), orderParams.getCount());
        assert result != null;
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");

    }

}
