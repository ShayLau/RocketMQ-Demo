package com.github.shaylau.rocketmq.demo.order;

import com.github.shaylau.rocketmq.demo.common.Constants;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 同步发送消息顺序生产者
 *
 * @author ShayLau
 * @date 2020/9/10 5:16 下午
 */
public class OrderlyProducer {
    public static void main(String[] args) throws MQClientException {

        OrderlyProducer orderlyProducer = new OrderlyProducer();
        List<OrderStep> messageList = orderlyProducer.buildOrders();

        DefaultMQProducer messageProducer = new DefaultMQProducer("producer-group");

        messageProducer.setNamesrvAddr(Constants.nameSrv);
        messageProducer.start();
        LocalDateTime localDateTime = LocalDateTime.now();
        String[] tags = new String[]{"TagB", "TagC", "TagD"};
        for (int i = 0; i < messageList.size(); i++) {
            OrderStep orderStep = messageList.get(i);
            //消息体：当前时间+Hello RocketMQ + orderStep信息
            String messageBody = localDateTime + ",Hello RocketMQ" + orderStep;
            Message message = new Message("broker-a", tags[i % tags.length], "Key" + i, messageBody.getBytes());
            try {
                SendResult sendResult = messageProducer.send(message, new MessageQueueSelector() {
                    /**
                     *
                     * @param mqs 消息队列的集合
                     * @param msg 消息信息
                     * @param arg 参数，与消息队列选择器一起工作
                     * @return
                     */
                    @Override
                        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        System.out.println(mqs.size());
                        Long orderId = (Long) arg;
                        long index = orderId % mqs.size();
                        return mqs.get((int) index);
                    }
                }, orderStep.getOrderId());
//                System.out.println(String.format("SendResult status:%s, queueId:%d, body:%s",
//                        sendResult.getSendStatus(), sendResult.getMessageQueue().getQueueId(), messageBody));
                System.out.println(sendResult);
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        messageProducer.shutdown();
    }

    /**
     * 生成模拟订单数据
     */
    private List<OrderStep> buildOrders() {
        List<OrderStep> orderList = new ArrayList<>();

        OrderStep orderDemo = new OrderStep(15103111039L, "创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103111065L, "创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103111039L, "付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103117235L, "创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103111065L, "付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103117235L, "付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103111065L, "完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103111039L, "推送");

        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103117235L, "完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep(15103111039L, "完成");
        orderList.add(orderDemo);

        return orderList;
    }

}

