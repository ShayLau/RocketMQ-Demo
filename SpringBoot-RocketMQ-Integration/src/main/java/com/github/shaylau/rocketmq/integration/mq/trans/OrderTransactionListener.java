package com.github.shaylau.rocketmq.integration.mq.trans;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 订单事务监听
 *
 * @author ShayLau
 * @date 2020/9/15 1:11 下午
 */
public class OrderTransactionListener implements TransactionListener {
    @Override
    public LocalTransactionState executeLocalTransaction(org.apache.rocketmq.common.message.Message msg, Object arg) {
        if (Integer.parseInt(arg.toString()) == 1) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        return LocalTransactionState.ROLLBACK_MESSAGE;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
