package com.github.shaylau.rocketmq.distributed.product;

import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author ShayLau
 * @date 2020/9/17 2:52 下午
 */
public class Test {
    public static void main(String[] args) {
        SubscribableChannel subscribableChannel=new DirectChannel();

        //subscribableChannel.subscribe(System.out::println);

        subscribableChannel.subscribe(message -> System.out.println("receive1接收到消息："+message.getPayload()));

        subscribableChannel.subscribe(message -> System.out.println("receive2接收到消息："+message.getPayload()));

        subscribableChannel.send(MessageBuilder.withPayload("Hello World").build());

        subscribableChannel.send(MessageBuilder.withPayload("Hello World").build());
    }
}
