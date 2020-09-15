package com.github.shaylau.rocketmq.integration.controller;

import com.github.shaylau.rocketmq.integration.model.Order;
import com.github.shaylau.rocketmq.integration.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShayLau
 * @date 2020/9/14 5:51 下午
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param order
     */
    @PostMapping("/")
    public void create(Order order) {
        //创建订单
        orderService.createOrder(order);
        //创建事务订单
        orderService.createTranOrder(order);
    }

}
