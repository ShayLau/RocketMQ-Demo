package com.github.shaylau.rocketmq.demo.filter;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

import java.time.LocalDateTime;

/**
 * 过滤消费消息
 *
 * <description>
 * 如若报错：
 * org.apache.rocketmq.client.exception.MQClientException: CODE: 1
 * DESC: The broker does not support consumer to filter message by SQL92
 * 需要在启动的 broker 中配置 enablePropertyFilter=true
 * </description>
 *
 * @author ShayLau
 * @date 2020/9/11 11:40 上午
 */
public class FilterConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer messageConsumer = new DefaultMQPushConsumer("producer-group");

        messageConsumer.setNamesrvAddr(Constants.nameSrv);

        //messageConsumer.subscribe("broker-a", MessageSelector.bySql("name = 'jack'"));

        messageConsumer.subscribe("broker-a", MessageSelector.bySql("age > 10 and name='jack' "));
        messageConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println("接收到消息：" + LocalDateTime.now());
            msgs.stream().forEach(messageExt -> System.out.println("consumeThread=" +
                    Thread.currentThread().getName() + ",queueId=" + messageExt.getQueueId() +
                    ", content:" + new String(messageExt.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        messageConsumer.start();
    }
}
