package com.github.shaylau.rocketmq.distributed.order.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单信息
 *
 * @author ShayLau
 * @date 2020/9/14 5:42 下午
 */
@Data
public class Order {

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 订单价格
     */
    private BigDecimal orderPrice;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 订单状态
     */
    private Integer orderStatus;
}
