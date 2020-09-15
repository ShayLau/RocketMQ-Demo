package com.github.shaylau.rocketmq.integration.mq.producer;


import com.github.shaylau.rocketmq.integration.model.Order;
import com.github.shaylau.rocketmq.integration.mq.trans.OrderTransactionListener;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

/**
 * 订单消息生产
 *
 * @author ShayLau
 * @date 2020/9/15 9:50 上午
 */
@Service
public class OrderMessageProducer extends ProducerMessageAbstract<Order> {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 发送消息示例代码
     *
     * @param order 订单信息
     */
    public void sendMessage(Order order) {
        ///发送消息
        Message<Order> message = new Message<Order>() {
            @Override
            public Order getPayload() {
                return order;
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        };
        //主题
        String topic = "order";
        //标签
        String tags = "";
        //描述信息
        String destination = topic + ":" + tags;
        //发送信息
        rocketMQTemplate.send(destination, message);
    }

    /**
     * 发送创建订单消息
     *
     * @param order 订单信息
     */
    public void sendOrderMessage(Order order) {
        //消息目的地
        String destination = messageDestination("order", "tag1");
        //消息内容
        Message<Order> message = createMessage(order, null);
        //发送消息
        rocketMQTemplate.send(destination, message);
    }

    /**
     * 发送事务消息
     *
     * @param order 订单信息
     * @return 发送状态
     */
    public void transactionOrderMessage(Order order) {
        //消息目的地
        String destination = messageDestination("order", "tag1");
        //消息内容
        Message<Order> message = createMessage(order, null);

        TransactionMQProducer producer = (TransactionMQProducer) rocketMQTemplate.getProducer();
        //事务监听器
        producer.setTransactionListener(new OrderTransactionListener());
        rocketMQTemplate.setProducer(producer);
        //发送信息
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, order.getOrderStatus());
        //发送状态
        SendStatus sendStatus = transactionSendResult.getSendStatus();

        System.out.println("事务消息发送状态：" + sendStatus);
    }

    /**
     * 顺序发送
     *
     * @param order 订单信息
     */
    public void sendOrderlyOrderMessage(Order order) {
        //消息目的地
        String destination = messageDestination("order", "tag1");
        //消息内容
        Message<Order> message = createMessage(order, null);
        //顺序发送（Spring封装 的 RocketMQ 顺序发送，使用了 hash来选择消息队列）
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(destination, message, order.getOrderId());

        System.out.println("顺序发送信息：" + sendResult);
    }

    /**
     * 自定义顺序，发送订单消息
     *
     * @param order 订单信息
     */
    public void sendCustomOrderlyOrderMessage(Order order) {

        //自定义队列选择器
        rocketMQTemplate.setMessageQueueSelector((mqs, msg, arg) -> {
            //比如订单号采用 000001 纯数字
            Integer orderId = Integer.parseInt(arg.toString());
            //取余
            int queueIndex = orderId % mqs.size();
            return mqs.get(queueIndex);
        });
        //消息目的地
        String destination = messageDestination("order", "tag1");
        //消息内容
        Message<Order> message = createMessage(order, null);
        //顺序发送(setMessageQueueSelector已将选择器设置为自定义队列选择器)
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(destination, message, order.getOrderId());
        System.out.println("顺序发送信息：" + sendResult);
    }

    /**
     * 延时消息
     * 业务场景：非实时的响应发送结果，所以rocketMQTemplate中的syncSend、sendAndReceive同步场景下不可用
     * 在rocketMQTemplate的消息发送发送中，asyncSend 方法可以使用
     *
     * @param order 订单消息
     */
    public void sendDelayOrderMessage(Order order) {
        //消息目的地
        String destination = messageDestination("order", "tag1");
        //消息内容
        Message<Order> message = createMessage(order, null);
        //异步发送消息
        rocketMQTemplate.asyncSend(destination, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println(e.getMessage());
            }
        }, 0, 1);
    }
}
