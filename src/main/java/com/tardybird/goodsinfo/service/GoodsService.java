package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.domain.Product;
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

    public Object getAllGoods(Integer pageNum,Integer pageSize)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goods=goodsDao.getAllGoods();
        PageInfo<Goods> pageInfo=new PageInfo<>(goods);
        return pageInfo;
    }


    public boolean createGoods(Goods goods) {
        if (goodsMapper.createGoods(goods) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public Goods getGoodsById(Integer id) {
        return goodsDao.getGoodsById(id);
    }


    public Goods searchGoodsByConditions(Goods goods) {
        boolean judgeGoodsSn = (goods.getGoodsSn() != null && !"".equals(goods.getGoodsSn()) && goods.getId() == null);
        boolean judgeGoodsId = (goods.getId() != null && (goods.getGoodsSn() == null || "".equals(goods.getGoodsSn())));
        if (judgeGoodsSn) {
            return goodsMapper.getGoodsByGoodsSn(goods.getGoodsSn());
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
