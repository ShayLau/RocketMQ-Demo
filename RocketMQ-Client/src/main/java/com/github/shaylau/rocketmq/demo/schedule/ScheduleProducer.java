package com.github.shaylau.rocketmq.demo.schedule;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.time.LocalDateTime;


/**
 * 延迟消息生产者
 *
 * @author ShayLau
 * @date 2020/9/11 11:17 上午
 */
public class ScheduleProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer messageProducer = new DefaultMQProducer("producer-group");

        messageProducer.setNamesrvAddr(Constants.nameSrv);
        messageProducer.start();
        String messageBody = "This is delay schedule message";
        Message message = new Message("broker-a", "schedule-tag-1", "Key", messageBody.getBytes());
        //延迟消息级别
        //private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
        message.setDelayTimeLevel(3);
        System.out.println("开始发送消息：" + LocalDateTime.now());
        messageProducer.send(message);
        messageProducer.shutdown();
    }
}
