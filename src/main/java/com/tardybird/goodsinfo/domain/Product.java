package com.tardybird.goodsinfo.domain;

import java.util.List;

/**
 * @author nick
 */
public class Product extends com.tardybird.goodsinfo.domain.entity.Product {

    private Goods goods;
    private List<Product> productList;
    private List<GoodsSpecificationItem> goodsSpecificationItemList;
}
