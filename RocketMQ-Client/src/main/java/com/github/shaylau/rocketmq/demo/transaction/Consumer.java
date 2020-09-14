package com.github.shaylau.rocketmq.demo.transaction;

        import com.github.shaylau.rocketmq.demo.common.Constants;
        import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
        import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
        import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
        import org.apache.rocketmq.client.exception.MQClientException;

/**
 * 事务消息消费
 *
 * @author ShayLau
 * @date 2020/9/14 2:25 下午
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("producer-group");
        consumer.setNamesrvAddr(Constants.nameSrv);
        //订阅事务消息
        consumer.subscribe("transaction", "*");
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            msgs.stream().forEach(messageExt -> {
                System.out.println("consumeThread=" + Thread.currentThread().getName() +
                        ",queueId=" + messageExt.getQueueId() + ", content:" + new String(messageExt.getBody()));
            });
            return ConsumeOrderlyStatus.SUCCESS;
        });
        consumer.start();

    }
}
