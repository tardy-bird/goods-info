package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author LENOVO
 */
@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    GoodsDao goodsDao;


    public Object getAllGoodsByConditions(String goodsSn, String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<GoodsPo> goodsPos = goodsMapper.getGoodsByCondition(goodsSn, name);
        List<Goods> goodsList = new ArrayList<>();
        for (GoodsPo goodsPo : goodsPos) {
            Goods goods = ObjectConversion.goodsPo2Goods(goodsPo);

            //TODO set actual POs
            goods.setBrandPo(null);
            goods.setGoodsCategoryPo(null);
            goods.setProductPoList(null);
            goods.setGrouponRule(null);
            goods.setShareRule(null);
            goods.setPresaleRule(null);

            goodsList.add(goods);
        }
        return new PageInfo<>(goodsList);
    }


    public Object getHotGoods(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsPo> goodsPos = goodsMapper.findHotGoods();

        List<Goods> goodsList = new ArrayList<>();
        for (GoodsPo goodsPo : goodsPos) {
            Goods goods = ObjectConversion.goodsPo2Goods(goodsPo);

            //TODO set actual POs
            goods.setBrandPo(null);
            goods.setGoodsCategoryPo(null);
            goods.setProductPoList(null);
            goods.setGrouponRule(null);
            goods.setShareRule(null);
            goods.setPresaleRule(null);

            goodsList.add(goods);
        }

        return new PageInfo<>(goodsList);
    }


    public void createGoods(Goods goods) {
        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);
        goodsMapper.createGoods(goodsPo);
    }

    public Goods getGoodsById(Integer id) {
        return goodsDao.getGoodsById(id);
    }

    public Integer getGoodsCount() {
        return goodsMapper.selectOnSaleGoods();
    }

    public GoodsCategory getGoodsCategory(Integer id) {
        GoodsCategoryPo goodsCategoryPo = goodsCategoryMapper.getCategory(id);
        return ObjectConversion.goodsCategoryPo2GoodsCategory(goodsCategoryPo);
    }

    public Integer updateGoods(Goods goods) {
        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);
        return goodsMapper.updateGoods(goodsPo);
    }

    public boolean deleteGood(Integer id) {
        return goodsMapper.deleteGoods(id) != 0;
    }
}
