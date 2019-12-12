package com.tardybird.goodsinfo.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author nick
 */
public class Brand extends com.tardybird.goodsinfo.entity.Brand implements Serializable {
    private List<Goods> goodsList;
}
