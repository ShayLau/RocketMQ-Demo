package com.github.shaylau.rocketmq.distributed.order.mq;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * 订单信息消费
 *
 * @author ShayLau
 * @date 2020/9/17 1:11 下午
 */
@Service
public class OrderConsumer {

//
//    /**
//     * 消费产品消息
//     *
//     * @param product
//     */
//    @StreamListener(Sink.INPUT)
//    public void consumerForProduct(Object product) {
//        System.out.println(product);
//    }

    /**
     * 监听消费消息
     * 监听之定义消息
     *
     * @param object
     */
    @StreamListener("topic-order")
    public void consumerForOrder(Object object) {
        System.out.println("接受自定义消费信息：" + object);
    }

}
