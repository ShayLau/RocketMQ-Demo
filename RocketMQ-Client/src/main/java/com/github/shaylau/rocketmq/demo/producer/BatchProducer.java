package com.github.shaylau.rocketmq.demo.producer;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 批量消息发送</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: 中图云创智能科技（北京）有限公司</p>
 * <p>Site: https://www.chinafocus.net/</p>
 *
 * @author Xuq
 * @since 2021/1/18
 */
public class BatchProducer {
    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("defaultGroup");
        //nameSrv
        producer.setNamesrvAddr(Constants.nameSrv);
        //启动 Product
        producer.start();

        List<Message> messageList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            messageList.add(new Message("topic-a", "hello world".getBytes()));
        }
        producer.send(messageList);

    }
}
