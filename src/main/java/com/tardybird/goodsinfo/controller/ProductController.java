package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nick
 */
@RestController
public class ProductController {

    final
    GoodsDao goodsDao;

    public ProductController(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @GetMapping("/product/{id}")
    public Object getProduct(@PathVariable("id") Integer id) {
        Product product = goodsDao.getProductById(id);
        return ResponseUtil.ok(product);
    }
}
