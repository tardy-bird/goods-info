package com.tardybird.goodsinfo.controller.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author DIX
 * @version 1.0
 * @description
 * @date 2019/12/6 19:48
 */
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {
    private String picUrl;

    private String price;

    private String safty_stock;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSafty_stock() {
        return safty_stock;
    }

    public void setSafty_stock(String safty_stock) {
        this.safty_stock = safty_stock;
    }
}
