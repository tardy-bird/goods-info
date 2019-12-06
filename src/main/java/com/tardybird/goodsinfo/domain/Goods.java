package com.tardybird.goodsinfo.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author nick
 */
public class Goods extends com.tardybird.goodsinfo.entity.Goods implements Serializable {
    private Brand brand;
    private GoodsCategory goodsCategory;
    private List<Product> productList;


    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
