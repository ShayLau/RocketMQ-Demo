package com.github.shaylau.rocketmq.distributed.order.service;

import com.github.shaylau.rocketmq.distributed.order.model.Order;

/**
 * @author ShayLau
 * @date 2020/9/17 11:41 上午
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order 订单信息
     */
    void createOrder(Order order);
}
