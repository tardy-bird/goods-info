package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Product;
import com.tardybird.goodsinfo.service.ProductService;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author nick
 */
@RestController
@RequestMapping("/goodsService")
public class ProductController {

    final
    GoodsDao goodsDao;

    final
    ProductService productService;

    public ProductController(GoodsDao goodsDao, ProductService productService) {
        this.goodsDao = goodsDao;
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Object getProduct(@PathVariable("id") Integer id) {
        com.tardybird.goodsinfo.entity.Product product = productService.getProductById(id);
        return ResponseUtil.ok(product);
    }

    /**
     * 管理员修改商品下的某个产品信息
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(@PathVariable Integer id,
                                    @RequestBody Product product) {
        if (product == null) {
            return ResponseUtil.fail();
        }
        product.setId(id);
        productService.updateProduct(product);
        return ResponseUtil.ok(product);
    }

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id x
     * @return x
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseUtil.ok();
    }
}
