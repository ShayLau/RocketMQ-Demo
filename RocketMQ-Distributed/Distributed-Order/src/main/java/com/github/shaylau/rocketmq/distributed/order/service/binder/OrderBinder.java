package com.github.shaylau.rocketmq.distributed.order.service.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author ShayLau
 * @date 2020/9/17 4:49 下午
 */
public interface OrderBinder {

    /**
     * Binder order input
     * @return
     */
    @Input("topic-order")
    MessageChannel orderConsumer();

    /**
     * Binder product output
     * @return
     */
    @Output("topic-product")
    SubscribableChannel producerToProduct();

}
