package com.github.shaylau.rocketmq.distributed.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * 分布式服务-产品服务
 *
 * @author ShayLau
 * @date 2020/9/17 11:22 上午
 */
@EnableBinding({Sink.class,Source.class})
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class);
    }
}
