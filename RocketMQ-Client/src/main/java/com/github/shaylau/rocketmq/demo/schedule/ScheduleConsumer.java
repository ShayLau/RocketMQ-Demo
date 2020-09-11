package com.github.shaylau.rocketmq.demo.schedule;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

import java.time.LocalDateTime;

/**
 * 延迟消息消费者
 *
 * @author ShayLau
 * @date 2020/9/11 11:17 上午
 */
public class ScheduleConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer messageConsumer = new DefaultMQPushConsumer("producer-group");

        messageConsumer.setNamesrvAddr(Constants.nameSrv);
        messageConsumer.subscribe("broker-a", "schedule-tag-1");
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
