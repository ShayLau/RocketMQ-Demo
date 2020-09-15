package com.github.shaylau.rocketmq.demo.transaction;

import com.github.shaylau.rocketmq.demo.common.Constants;
import com.github.shaylau.rocketmq.demo.common.OrderStatusEnum;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 事务消息生产者
 *
 * @author ShayLau
 * @date 2020/9/14 2:25 下午
 */
public class Producer {

    public static void main(String[] args) throws MQClientException {

        //事务生产者
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("defaultGroup");
        //设置注册中心 Namerv 地址
        transactionMQProducer.setNamesrvAddr(Constants.nameSrv);
        //设置事务监听器
        transactionMQProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                //订单完成则提交事务
                if (arg.equals(OrderStatusEnum.COMPLETE)) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if (arg.equals(OrderStatusEnum.ERROR)) {
                    //订单失败这回滚事务
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else {
                    return LocalTransactionState.UNKNOW;
                }
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        //启动事务生产者
        transactionMQProducer.start();

        //本地事务执行完成后要发送的消息
        Message message = new Message("transaction", "tag1", null, "TransAction Info".getBytes());


        //事务消息结果，参数：消息体、参数
        TransactionSendResult sendResult1 = transactionMQProducer.sendMessageInTransaction(message, OrderStatusEnum.COMPLETE);
        System.out.println("完成的订单，事务执行结果:" + sendResult1);

        //事务消息结果，参数：消息体、参数
        TransactionSendResult sendResult2 = transactionMQProducer.sendMessageInTransaction(message, OrderStatusEnum.ERROR);
        System.out.println("错误的订单，事务执行结果:" + sendResult2);

    }
}
