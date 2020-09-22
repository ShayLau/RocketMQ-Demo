package com.github.shaylau.rocketmq.distributed.product.service.impl;

import com.github.shaylau.rocketmq.distributed.product.model.Product;
import com.github.shaylau.rocketmq.distributed.product.mq.ProductProducer;
import com.github.shaylau.rocketmq.distributed.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShayLau
 * @date 2020/9/17 1:19 下午
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductProducer productProducer;


    @Override
    public void productStockUpdate(Product product) {
        productProducer.sentToStock(product);
    }
}
