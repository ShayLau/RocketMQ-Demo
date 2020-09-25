package com.github.shaylau.rocketmq.demo.producer;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 同步消息息生产者
 *
 * @author ShayLau
 * @date 2020/9/10 9:48 上午
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("defaultGroup");
        //nameSrv
        producer.setNamesrvAddr(Constants.nameSrv);
        //启动 Product
        producer.start();

        //准备消息
        Message message = new Message();

        //订单主题
        message.setTopic("topic-order");


        //产品主题
        //message.setTopic("topic-product");

        //标签
        message.setTags("");

        //消息内容
        message.setBody("Sync message:  Hello World".getBytes());

        //发送消息
        producer.send(message);

        //同步阻塞  等待消息发送结果
        SendResult sendResult = producer.send(message);


        System.out.println(sendResult);

        producer.shutdown();
    }
}
