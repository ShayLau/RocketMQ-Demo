package com.github.shaylau.rocketmq.integration.mq.consumer;

import com.github.shaylau.rocketmq.integration.model.Order;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author ShayLau
 * @date 2020/9/14 5:30 下午
 */
@Component
@RocketMQMessageListener(topic = "order", consumerGroup = "order-consumer-group")
public class OrderConsumer implements RocketMQListener<Order> {

    /**
     * 消费主题
     *
     * @param message
     */
    @Override
    public void onMessage(Order message) {
        System.out.println("消息消费：" + message);
    }
}
