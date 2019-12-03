package com.tardybird.goodsinfo.controller;

import org.springframework.web.bind.annotation.*;

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

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 根据条件搜索品牌
     */
    @GetMapping("/admins/brands")
    public Object getBrandsByCondition() {
        return null;
    }

    /**
     * 创建一个品牌
     */
    @PostMapping("/brands")
    public void addBrand() {
    }

    /**
     * 修改单个品牌的信息
     */
    @PutMapping("/brands/{id}")
    public void updateBrand(@PathVariable("id") Long id) {
    }

    /**
     * 删除一个品牌
     */
    @DeleteMapping("/brands/{id}")
    public void deleteBrand(@PathVariable("id") Long id) {
    }

}
