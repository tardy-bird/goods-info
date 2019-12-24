package com.tardybird.goodsinfo.util.converter;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;

import java.util.function.Function;

/**
 * @author nick
 */
public class GoodsCategoryConverter extends Converter<GoodsCategoryPo, GoodsCategory> {

    public GoodsCategoryConverter() {
        super(new GoodsCategoryPoFunction(), new GoodsCategoryFunction());
    }

    static class GoodsCategoryPoFunction implements Function<GoodsCategoryPo, GoodsCategory> {

        @Override
        public GoodsCategory apply(GoodsCategoryPo goodsCategoryPo) {
            if (goodsCategoryPo == null) {
                return null;
            }

            GoodsCategory goodsCategory = new GoodsCategory();

            goodsCategory.setId(goodsCategoryPo.getId());
            goodsCategory.setName(goodsCategoryPo.getName());
            goodsCategory.setPid(goodsCategoryPo.getPid());
            goodsCategory.setGmtCreate(goodsCategoryPo.getGmtCreate());
            goodsCategory.setPicUrl(goodsCategoryPo.getPicUrl());
            goodsCategory.setGmtModified(goodsCategoryPo.getGmtModified());
            goodsCategory.setBeDeleted(goodsCategoryPo.getBeDeleted());

            return goodsCategory;
        }
    }

    static class GoodsCategoryFunction implements Function<GoodsCategory, GoodsCategoryPo> {

        @Override
        public GoodsCategoryPo apply(GoodsCategory goodsCategory) {
            if (goodsCategory == null) {
                return null;
            }

            GoodsCategoryPo goodsCategoryPo = new GoodsCategoryPo();

            goodsCategoryPo.setId(goodsCategory.getId());
            goodsCategoryPo.setName(goodsCategory.getName());
            goodsCategoryPo.setPid(goodsCategory.getPid());
            goodsCategoryPo.setPicUrl(goodsCategory.getPicUrl());
            goodsCategoryPo.setGmtCreate(goodsCategory.getGmtCreate());
            goodsCategoryPo.setGmtModified(goodsCategory.getGmtModified());
            goodsCategoryPo.setBeDeleted(goodsCategory.getBeDeleted());

            return goodsCategoryPo;
        }
    }
}
