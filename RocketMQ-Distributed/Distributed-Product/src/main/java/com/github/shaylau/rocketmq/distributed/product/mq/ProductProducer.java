package com.github.shaylau.rocketmq.distributed.product.mq;

import com.github.shaylau.rocketmq.distributed.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author ShayLau
 * @date 2020/9/17 1:20 下午
 */
@Service
public class ProductProducer {


    @Autowired
    private Source source;


    /**
     * 发送消息到订单
     *
     * @param product
     */
    public void sendToOrder(Product product) {
        Message<Product> message = MessageBuilder.withPayload(product).build();
        source.output().send(message);
    }

}
