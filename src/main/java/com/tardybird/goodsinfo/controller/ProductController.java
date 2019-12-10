package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id
     * @return
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(@PathVariable Integer id,
                                    @RequestBody Product product) {
        // TODO
        return null;
    }
}
