package com.github.shaylau.rocketmq.demo.filter;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.time.LocalDateTime;

/**
 * @author ShayLau
 * @date 2020/9/11 11:39 上午
 */
public class FilterProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer messageProducer = new DefaultMQProducer("producer-group");

        messageProducer.setNamesrvAddr(Constants.nameSrv);
        messageProducer.start();


        String messageBody = "This is filter message";
        Message message = new Message("broker-a", "filter-tag-1", "Key", messageBody.getBytes());
        //设置一些属性，已提供过滤



        message.putUserProperty("name", "jack");
        message.putUserProperty("age", "19");


        System.out.println("开始发送消息：" + LocalDateTime.now());
        messageProducer.send(message);
        messageProducer.shutdown();
    }
}
