package com.tardybird.goodsinfo.controller.vo;

import com.tardybird.goodsinfo.entity.Goods;
import com.tardybird.goodsinfo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author LENOVO
 */
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo implements Serializable {
    private Goods goods;

    List<Product> products;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
