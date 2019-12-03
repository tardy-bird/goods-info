package com.tardybird.goodsinfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nick
 */
@RestController
public class BrandController {

    /*
     * ========= following are wx apis ==============
     */

    /**
     * 查看所有品牌
     */
    @GetMapping("/brands")
    public Object getAllBrands() {
        return null;
    }

    /**
     * 查看品牌详情
     */
    @GetMapping("/brands/{id}")
    public Object getBrandDetails(@PathVariable("id") Long id) {
        return null;
    }

}
