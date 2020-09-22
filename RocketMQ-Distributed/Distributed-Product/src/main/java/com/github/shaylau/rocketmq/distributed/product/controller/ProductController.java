package com.github.shaylau.rocketmq.distributed.product.controller;

import com.github.shaylau.rocketmq.distributed.product.model.Product;
import com.github.shaylau.rocketmq.distributed.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShayLau
 * @date 2020/9/17 1:27 下午
 */
@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * 修改产品库存
     *
     * @param product
     */
    @PostMapping("")
    public void updateStock(Product product) {
        productService.productStockUpdate(product);
    }

}
