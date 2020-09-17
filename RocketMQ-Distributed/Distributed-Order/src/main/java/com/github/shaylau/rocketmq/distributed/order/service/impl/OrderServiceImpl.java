package com.github.shaylau.rocketmq.distributed.order.service.impl;

import com.github.shaylau.rocketmq.distributed.order.model.Order;
import com.github.shaylau.rocketmq.distributed.order.mq.OrderProducer;
import com.github.shaylau.rocketmq.distributed.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShayLau
 * @date 2020/9/17 11:42 上午
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderProducer orderProducer;

    @Override
    public void createOrder(Order order) {
        //orderProducer.sendMessageToProduct(order);

        orderProducer.sendMessageToProduct2(order);
    }
}
