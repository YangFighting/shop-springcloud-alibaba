package io.yang.shop.order.service;

import io.yang.shop.params.OrderParams;

/**
 * @author zhangyang03
 * @Description 订单业务接口
 * @create 2022-07-24 15:12
 */
public interface OrderService {

    void saveOrder(OrderParams orderParams);
}
