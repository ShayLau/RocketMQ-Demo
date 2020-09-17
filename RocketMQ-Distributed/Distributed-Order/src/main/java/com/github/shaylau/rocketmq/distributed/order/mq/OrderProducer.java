package com.github.shaylau.rocketmq.distributed.order.mq;

import com.github.shaylau.rocketmq.distributed.order.model.Order;
import com.github.shaylau.rocketmq.distributed.order.service.binder.OrderBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author ShayLau
 * @date 2020/9/17 11:43 上午
 */
@Service
public class OrderProducer {

///
/*    @Autowired
    private Source source;*/

    @Autowired
    private OrderBinder orderBinder;


    /**
     * 使用 Spring Cloud Stream 自带 Source的 Binder 设置（Input、Output)发送消息
     *
     * @param order
     */
    ///
/*
    public void sendMessageToProduct(Order order) {
        Message<Order> orderMessage = MessageBuilder.withPayload(order).build();
        source.output().send(orderMessage);
    }
*/

    /**
     * 使用自定义 Binder 发送消息（）
     *
     * @param order
     */
    public void sendMessageToProduct2(Order order) {
        orderBinder.producerToProduct().send(MessageBuilder.withPayload(order).build());
    }
}
