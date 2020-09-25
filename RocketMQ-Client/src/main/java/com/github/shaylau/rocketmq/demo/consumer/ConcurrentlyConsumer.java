package com.github.shaylau.rocketmq.demo.consumer;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;


/**
 * @author ShayLau
 * @date 2020/9/10 11:35 上午
 */
public class ConcurrentlyConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("defaultConsumerGroup");
        consumer.setNamesrvAddr(Constants.nameSrv);



        //订阅 Topic 中的所有所有 Tag 信息 ，expression * 表示所有Tag   "tag1" 表示 tag1对应的 tag1
        consumer.subscribe("topic-order", "*");


        //expression * 表示所有Tag   "tag1" 表示 tag1对应的 tag1
        ///consumer.subscribe("broker-a", "tag1");

        //消息消费类型： 默认 Clustering 负载均衡  Broadcasting  广播
        //consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {

            System.out.println(msgs);

            msgs.stream().forEach(msgExt -> System.out.println(new String(msgExt.getBody())));



            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();

    }
}
