package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.po.GoodsPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/5 21:20
 */
@Service
public class GoodsCategoryService {

    final
    GoodsCategoryMapper goodsCategoryMapper;
    final GoodsMapper goodsMapper;

    public GoodsCategoryService(GoodsCategoryMapper goodsCategoryMapper, GoodsMapper goodsMapper) {
        this.goodsCategoryMapper = goodsCategoryMapper;
        this.goodsMapper = goodsMapper;
    }

    public List<GoodsCategory> getAllCategories() {
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryMapper.getAllCategories();
        return getGoodsCategories(goodsCategoryPos);
    }

    public List<GoodsCategory> getLevelOneCategories() {
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryMapper.getLevelOneCategories();
        return getGoodsCategories(goodsCategoryPos);
    }

    public GoodsCategory getCategory(Integer id) {
        GoodsCategoryPo goodsCategoryPo = goodsCategoryMapper.getCategory(id);
        return ObjectConversion.goodsCategoryPo2GoodsCategory(goodsCategoryPo);
    }

    public List<GoodsCategory> getLevelTwoByPid(Integer pid) {

        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryMapper.getLevelTwoByPid(pid);

        return getGoodsCategories(goodsCategoryPos);
    }

    private List<GoodsCategory> getGoodsCategories(List<GoodsCategoryPo> goodsCategoryPos) {
        List<GoodsCategory> goodsCategoryList = new ArrayList<>();

        for (GoodsCategoryPo goodsCategoryPo : goodsCategoryPos) {
            GoodsCategory goodsCategory = ObjectConversion.goodsCategoryPo2GoodsCategory(goodsCategoryPo);

            List<GoodsPo> goodsPos = goodsMapper.findGoodsByGoodsCategoryId(String.valueOf(goodsCategory.getId()));
            goodsCategory.setGoodsPoList(goodsPos);

            goodsCategoryList.add(goodsCategory);
        }
        return goodsCategoryList;
    }

    public Boolean createCategory(GoodsCategory goodsCategory) {
        GoodsCategoryPo goodsCategoryPo = ObjectConversion.goodsCategory2GoodsCategoryPo(goodsCategory);
        Integer affectedRows = goodsCategoryMapper.createCategory(goodsCategoryPo);
        return affectedRows > 0;
    }

    public Boolean updateCategory(GoodsCategory goodsCategory) {
        GoodsCategoryPo goodsCategoryPo = ObjectConversion.goodsCategory2GoodsCategoryPo(goodsCategory);
        Integer affectedRows = goodsCategoryMapper.updateCategory(goodsCategoryPo);
        return affectedRows > 0;
    }


    public Boolean deleteCategory(Integer id) {
        GoodsCategoryPo goodsCategoryPo = goodsCategoryMapper.getCategory(id);

        // 一级分类
        if (goodsCategoryPo.getPid() == null) {
            List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryMapper.getLevelTwoByPid(id);

            for (GoodsCategoryPo categoryPo : goodsCategoryPos) {
                Integer categoryId = categoryPo.getId();
                goodsCategoryMapper.deleteCategory(categoryId);

                // 更新相关商品的种类
                Integer status = goodsMapper.updateCategoryId(categoryId);
                if (status <= 0) {
                    return false;
                }
            }

        }

        Integer affectedRows = goodsCategoryMapper.deleteCategory(id);
        goodsMapper.updateCategoryId(id);
        return affectedRows > 0;
    }


}
