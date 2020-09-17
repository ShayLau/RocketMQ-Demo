package com.github.shaylau.rocketmq.distributed.order.controller;

import com.github.shaylau.rocketmq.distributed.order.model.Order;
import com.github.shaylau.rocketmq.distributed.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShayLau
 * @date 2020/9/17 11:38 上午
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    public void add(Order order) {
        orderService.createOrder(order);
    }
}
