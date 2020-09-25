package com.github.shaylau.rocketmq.demo.producer;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author ShayLau
 * @date 2020/9/10 3:11 下午
 */
public class OneWayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("defaultGroup");
        //nameSrv
        producer.setNamesrvAddr(Constants.nameSrv);
        //启动 Product
        producer.start();


        Message message = new Message();
        message.setTopic("broker-a");
        message.setTags("tag1");
        message.setBody("Hello World".getBytes());

        producer.sendOneway(message);


        producer.shutdown();
    }
}
