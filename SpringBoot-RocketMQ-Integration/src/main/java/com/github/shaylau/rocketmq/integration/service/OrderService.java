package com.github.shaylau.rocketmq.integration.service;

import com.github.shaylau.rocketmq.integration.model.Order;


/**
 * @author ShayLau
 * @date 2020/9/14 5:42 下午
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order 订单信息
     */
    void createOrder(Order order);

    /**
     * 创建事务订单
     *
     * @param order 订单信息
     */
    void createTranOrder(Order order);


}
