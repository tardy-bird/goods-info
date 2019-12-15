package com.tardybird.goodsinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.mapper.ProductMapper;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author LENOVO
 */
@Service
public class GoodsService {

    final GoodsMapper goodsMapper;
    final GoodsCategoryMapper goodsCategoryMapper;
    final GoodsDao goodsDao;
    final ProductMapper productMapper;

    public GoodsService(GoodsMapper goodsMapper,
                        GoodsCategoryMapper goodsCategoryMapper,
                        GoodsDao goodsDao, ProductMapper productMapper) {
        this.goodsMapper = goodsMapper;
        this.goodsCategoryMapper = goodsCategoryMapper;
        this.goodsDao = goodsDao;
        this.productMapper = productMapper;
    }


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


    public Boolean createGoods(Goods goods) {
        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);
        Integer affectedRows = goodsMapper.createGoods(goodsPo);
        return affectedRows > 0;
    }

    public Goods getGoodsById(Integer id) {
        GoodsPo goodsPo = goodsDao.getGoodsById(id);
        return ObjectConversion.goodsPo2Goods(goodsPo);
    }


//    public GoodsCategory getGoodsCategory(Integer id) {
//        GoodsCategoryPo goodsCategoryPo = goodsCategoryMapper.getCategory(id);
//        return ObjectConversion.goodsCategoryPo2GoodsCategory(goodsCategoryPo);
//    }

    public Boolean updateGoods(Goods goods) {
        GoodsPo goodsPo = ObjectConversion.goods2GoodsPo(goods);
        Integer affectedRows = goodsMapper.updateGoods(goodsPo);
        return affectedRows > 0;
    }

    @Transactional
    public Boolean deleteGood(Integer id) {
        Integer deleteProductRows = productMapper.updateProductByGoodsId(id);
        Integer deleteGoodsRows = goodsMapper.deleteGoods(id);
        return deleteProductRows > 0 && deleteGoodsRows > 0;
    }
}
