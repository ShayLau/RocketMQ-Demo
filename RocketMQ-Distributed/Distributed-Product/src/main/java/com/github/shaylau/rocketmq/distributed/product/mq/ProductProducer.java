package com.github.shaylau.rocketmq.distributed.product.mq;

import com.github.shaylau.rocketmq.distributed.product.model.Product;
import com.github.shaylau.rocketmq.distributed.product.mq.binder.CustomChannelBinder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomChannelBinder channelBinder;


    /**
     * 发送消息
     *
     * @param product
     */
    public void send(Product product) {
        Message<Product> message = MessageBuilder.withPayload(product).build();
        source.output().send(message);
    }

    /**
     * 发送消息到订单
     * @param product
     */
    public void sendToOrder(Product product) {
        Message<Product> message = MessageBuilder.withPayload(product).build();
        channelBinder.sendToOrderChannel().send(message);
    }

    /**
     * 发送消息到订单
     * @param product
     */
    public void sentToStock(Product product) {
        Message<Product> message = MessageBuilder.withPayload(product).build();
        channelBinder.sendToStockChannel().send(message);
    }


}
