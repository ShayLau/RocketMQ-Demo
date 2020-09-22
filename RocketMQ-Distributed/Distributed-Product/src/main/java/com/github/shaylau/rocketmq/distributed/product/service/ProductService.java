package com.github.shaylau.rocketmq.distributed.product.service;

import com.github.shaylau.rocketmq.distributed.product.model.Product;

/**
 * @author ShayLau
 * @date 2020/9/17 1:19 下午
 */
public interface ProductService {

    /**
     * 商品库存修改
     *
     * @param product
     */
    void productStockUpdate(Product product);
}
