package com.github.shaylau.rocketmq.distributed.order;

import com.github.shaylau.rocketmq.distributed.order.service.binder.OrderBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * 分布式服务-订单服务
 *
 * @author ShayLau
 * @date 2020/9/17 11:16 上午
 */
@EnableBinding({OrderBinder.class})
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }
}
