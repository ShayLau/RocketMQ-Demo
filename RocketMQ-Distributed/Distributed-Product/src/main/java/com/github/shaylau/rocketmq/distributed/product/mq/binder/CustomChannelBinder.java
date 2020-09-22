package com.github.shaylau.rocketmq.distributed.product.mq.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义通道 Binder
 *
 * @author ShayLau
 * @date 2020/9/21 4:21 下午
 */
public interface CustomChannelBinder {

    /**
     * 接收产品 Channel 消息
     *
     * @return
     */
    @Input("Topic-product")
    MessageChannel productInputChannel();

    /**
     * 发送消息到订单 Channel 消息
     *
     * @return
     */
    @Output("Topic-order")
    MessageChannel sendToOrderChannel();


    /**
     * 发送消息到库存 Channel 消息
     *
     * @return
     */
    @Output("Topic-stock")
    MessageChannel sendToStockChannel();


}
