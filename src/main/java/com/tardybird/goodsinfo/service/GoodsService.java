package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.controller.vo.GoodsVo;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Goods> goodsList = goodsMapper.getGoodsByCondition(goodsSn, name);
        return new PageInfo<>(goodsList);
    }


    public Object getHotGoods(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodsMapper.getHotGoods();
        return new PageInfo<>(goods);
    }


    public void createGoods(Goods goods) {
        goodsMapper.createGoods(goods);
    }

    public GoodsVo getGoodsById(Integer id) {
        return goodsDao.getGoodsById(id);
    }

    public Integer getGoodsCount() {
        return goodsMapper.selectOnSaleGoods();
    }

    public GoodsCategory getGoodsCategory(Integer id) {
        return goodsCategoryMapper.getCategory(id);
    }

    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
    }

    public boolean deleteGood(Integer id) {
        return goodsMapper.deleteGoods(id) != 0;
    }
}
