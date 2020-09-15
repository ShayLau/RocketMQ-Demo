package com.github.shaylau.rocketmq.integration.mq.producer;


import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

/**
 * 生产消息抽象类
 *
 * @author ShayLau
 * @date 2020/9/15 1:14 下午
 */
public abstract class ProducerMessageAbstract<T> {

    /**
     * 创建消息
     *
     * @param t   消息实体类
     * @param map messageHeaders map
     * @return 消息
     */
    public Message<T> createMessage(T t, Map<String, Object> map) {
        Message<T> message = new Message<T>() {
            @Override
            public T getPayload() {
                return t;
            }

            @Override
            public MessageHeaders getHeaders() {
                MessageHeaders messageHeaders = new MessageHeaders(map);
                return messageHeaders;
            }
        };
        return message;
    }

    /**
     * 设置消息目标
     *
     * @param topic 消息主题
     * @param tags  消息标签
     * @return 消息目的地
     */
    public String messageDestination(String topic, String tags) {
        StringBuffer destinationBuffer = new StringBuffer();
        destinationBuffer.append(topic);
        destinationBuffer.append(":");
        destinationBuffer.append(tags);
        return destinationBuffer.toString();
    }
}
