package com.tardybird.goodsinfo.controller.vo;

import com.tardybird.goodsinfo.entity.Product;

import java.util.List;

public class ProductVo {

    /**
     * 这个产品自己的product信息
     */
    private Product product;

    /**
     * 团购商品的其他组合产品信息
     */
    private List<Product> subProducts;

    @Override
    public String toString() {
        return "ProductVo{" +
                "product=" + product +
                ", subProducts=" + subProducts +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<Product> subProducts) {
        this.subProducts = subProducts;
    }
}
