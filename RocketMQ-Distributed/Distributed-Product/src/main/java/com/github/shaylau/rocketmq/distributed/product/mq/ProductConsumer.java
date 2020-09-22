package com.github.shaylau.rocketmq.distributed.product.mq;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author ShayLau
 * @date 2020/9/17 1:20 下午
 */
@Service
public class ProductConsumer {


    /**
     * 消费库存消息
     *
     * @param object 库存消息体
     */
    @StreamListener("Topic-stock")
    public void consumerForStock(Object object) {
        System.out.println("收到库存消息：" + object);
    }


    /**
     * 消费订单消息
     *
     * @param object 订单消息体
     */
    @StreamListener("Topic-order")
    public void consumerForOrder(Object object) {
        System.out.println("收到订单消息：" + object);
    }
}
