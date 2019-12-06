package com.tardybird.goodsinfo.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author nick
 */
public class Product extends com.tardybird.goodsinfo.entity.Product implements Serializable {
    private Goods goods;
    private List<Product> productList;
    private List<GoodsSpecificationItem> goodsSpecificationItemList;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<GoodsSpecificationItem> getGoodsSpecificationItemList() {
        return goodsSpecificationItemList;
    }

    public void setGoodsSpecificationItemList(List<GoodsSpecificationItem> goodsSpecificationItemList) {
        this.goodsSpecificationItemList = goodsSpecificationItemList;
    }

    @Override
    public String toString() {
        return "Product{" + getId()+
                "goods=" + goods +
                ", productList=" + productList +
                ", goodsSpecificationItemList=" + goodsSpecificationItemList +
                '}';
    }
}
