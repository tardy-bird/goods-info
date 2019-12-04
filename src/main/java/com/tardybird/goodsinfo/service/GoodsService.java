package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.controller.vo.GoodsVo;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

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
        boolean judgeGoodsSn = (goods.getNameSn() != null && !goods.getNameSn().equals("") && goods.getId() == null);
        boolean judgeGoodsId = (goods.getId() != null && (goods.getNameSn() == null || goods.getNameSn().equals("")));
        if (judgeGoodsSn) {
            return goodsMapper.getGoodsByGoodsSn(goods.getNameSn());
        } else if (judgeGoodsId) {
            return goodsMapper.getGoodsById(goods.getId());
        } else {
            return null;
        }
    }

    public Integer getGoodsCount(){

    }
}
