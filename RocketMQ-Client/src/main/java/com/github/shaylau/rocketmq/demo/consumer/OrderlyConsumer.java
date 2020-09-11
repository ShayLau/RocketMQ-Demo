package com.github.shaylau.rocketmq.demo.consumer;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author ShayLau
 * @date 2020/9/10 3:34 下午
 */
public class OrderlyConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("defaultConsumerGroup");
        consumer.setNamesrvAddr(Constants.nameSrv);
        //订阅 Topic 中的所有所有 Tag 信息 ，expression * 表示所有Tag   "tag1" 表示 tag1对应的 tag1
        consumer.subscribe("broker-a", "*");
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            System.out.println(msgs);
            msgs.stream().forEach(msgExt -> System.out.println(new String(msgExt.getBody())));
            return ConsumeOrderlyStatus.SUCCESS;
        });
        consumer.start();

    }
}
