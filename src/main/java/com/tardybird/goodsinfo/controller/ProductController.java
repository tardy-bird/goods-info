package com.tardybird.goodsinfo.controller;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nick
 */
@RestController
public class ProductController {

    @Autowired
    GoodsDao goodsDao;


    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        return goodsDao.getProductById(id);
    }
}
