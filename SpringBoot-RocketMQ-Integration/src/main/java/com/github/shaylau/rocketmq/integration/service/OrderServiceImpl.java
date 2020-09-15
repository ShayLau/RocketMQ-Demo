package com.github.shaylau.rocketmq.integration.service;

import com.github.shaylau.rocketmq.integration.model.Order;
import com.github.shaylau.rocketmq.integration.mq.producer.OrderMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShayLau
 * @date 2020/9/14 5:38 下午
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMessageProducer orderMessageProducer;

    /**
     * 发送订单信息
     *
     * @param order 订单信息
     */
    @Override
    public void createOrder(Order order) {
        //创建订单逻辑
        //.....
        //消息通讯
        orderMessageProducer.sendOrderMessage(order);
    }

    /**
     * 发送订单事务信息
     *
     * @param order 订单信息
     */
    @Override
    public void createTranOrder(Order order) {
        //创建订单逻辑
        //.....
        //发送事务订单
        SendStatus sendStatus = orderMessageProducer.transactionOrderMessage(order);
        log.info("发送事务订单：" + sendStatus);
    }
}
