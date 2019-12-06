package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author LENOVO
 */
@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    public boolean createGoods(Goods goods) {
        if (goodsMapper.createGoods(goods) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public Goods getGoodsById(Integer id) {
        return goodsMapper.getGoodsById(id);
    }

    public Goods searchGoodsByConditions(Goods goods) {
        boolean judgeGoodsSn = (goods.getNameSn() != null && !"".equals(goods.getNameSn()) && goods.getId() == null);
        boolean judgeGoodsId = (goods.getId() != null && (goods.getNameSn() == null || "".equals(goods.getNameSn())));
        if (judgeGoodsSn) {
            return goodsMapper.getGoodsByGoodsSn(goods.getNameSn());
        } else if (judgeGoodsId) {
            return goodsMapper.getGoodsById(goods.getId());
        } else {
            return null;
        }
    }

    public Integer getGoodsCount() {
        return goodsMapper.selectOnSaleGoods();
    }

    public GoodsCategory getGoodsCategory(Integer id){
        return goodsCategoryMapper.getCategory(id);
    }

    public boolean deleteGood(Integer id){
        if (goodsMapper.deleteGoods(id)!=0) {
            return true;
        }
        else {
            return false;
        }
    }
}
