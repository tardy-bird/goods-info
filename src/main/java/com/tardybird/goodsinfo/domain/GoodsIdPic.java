package com.tardybird.goodsinfo.domain;

import java.io.Serializable;

/**
 * @author DIX
 * @version 1.0
 * @description
 * @date 2019/12/8 20:07
 */
public class GoodsIdPic implements Serializable {

    private Integer id;

    private String picUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
