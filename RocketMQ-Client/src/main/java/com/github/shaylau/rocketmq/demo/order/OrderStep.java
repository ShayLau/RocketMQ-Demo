package com.github.shaylau.rocketmq.demo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单实体
 *
 * @author ShayLau
 * @date 2020/9/10 5:17 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStep {

    /**
     * 订单 id
     */
    private long orderId;
    /**
     * 订单描述
     */
    private String desc;

    @Override
    public String toString() {
        return "OrderStep{orderId:" + orderId + ",desc:" + desc + "}";
    }
}
