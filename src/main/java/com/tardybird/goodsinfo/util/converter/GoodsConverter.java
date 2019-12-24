package com.tardybird.goodsinfo.util.converter;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.po.GoodsPo;

import java.util.function.Function;

/**
 * @author nick
 */
public class GoodsConverter extends Converter<GoodsPo, Goods> {

    public GoodsConverter() {
        super(new GoodsPoFunction(), new GoodsFunction());
    }

    static class GoodsPoFunction implements Function<GoodsPo, Goods> {

        @Override
        public Goods apply(GoodsPo goodsPo) {
            if (goodsPo == null) {
                return null;
            }

            Goods goods = new Goods();

            goods.setId(goodsPo.getId());
            goods.setGmtCreate(goodsPo.getGmtCreate());
            goods.setGmtModified(goodsPo.getGmtModified());
            goods.setName(goodsPo.getName());
            goods.setGoodsSn(goodsPo.getGoodsSn());
            goods.setShortName(goodsPo.getShortName());
            goods.setDescription(goodsPo.getDescription());
            goods.setBrief(goodsPo.getBrief());
            goods.setPicUrl(goodsPo.getPicUrl());
            goods.setDetail(goodsPo.getDetail());
            goods.setStatusCode(goodsPo.getStatusCode());
            goods.setShareUrl(goodsPo.getShareUrl());
            goods.setGallery(goodsPo.getGallery());
            goods.setGoodsCategoryId(goodsPo.getGoodsCategoryId());
            goods.setBrandId(goodsPo.getBrandId());
            goods.setBeDeleted(goodsPo.getBeDeleted());
            goods.setWeight(goodsPo.getWeight());
            goods.setVolume(goodsPo.getVolume());
            goods.setSpecialFreightId(goodsPo.getSpecialFreightId());
            goods.setBeSpecial(goodsPo.getBeSpecial());
            goods.setPrice(goodsPo.getPrice());

            return goods;
        }
    }

    static class GoodsFunction implements Function<Goods, GoodsPo> {

        @Override
        public GoodsPo apply(Goods goods) {
            if (goods == null) {
                return null;
            }

            GoodsPo goodsPo = new GoodsPo();

            goodsPo.setId(goods.getId());
            goodsPo.setGmtCreate(goods.getGmtCreate());
            goodsPo.setGmtModified(goods.getGmtModified());
            goodsPo.setName(goods.getName());
            goodsPo.setGoodsSn(goods.getGoodsSn());
            goodsPo.setShortName(goods.getShortName());
            goodsPo.setDescription(goods.getDescription());
            goodsPo.setBrief(goods.getBrief());
            goodsPo.setPicUrl(goods.getPicUrl());
            goodsPo.setDetail(goods.getDetail());
            goodsPo.setStatusCode(goods.getStatusCode());
            goodsPo.setShareUrl(goods.getShareUrl());
            goodsPo.setGallery(goods.getGallery());
            goodsPo.setGoodsCategoryId(goods.getGoodsCategoryId());
            goodsPo.setBrandId(goods.getBrandId());
            goodsPo.setBeDeleted(goods.getBeDeleted());
            goodsPo.setWeight(goods.getWeight());
            goodsPo.setVolume(goods.getVolume());
            goodsPo.setSpecialFreightId(goods.getSpecialFreightId());
            goodsPo.setBeSpecial(goods.getBeSpecial());
            goodsPo.setPrice(goods.getPrice());

            return goodsPo;
        }
    }
}
