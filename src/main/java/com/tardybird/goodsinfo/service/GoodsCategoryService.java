package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DIX
 * @version 1.0
 * @date 2019/12/5 21:20
 */
@Service
public class GoodsCategoryService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

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

            //TODO set actual Goods List
            goodsCategory.setGoodsPoList(null);

            goodsCategoryList.add(goodsCategory);
        }
        return goodsCategoryList;
    }

    public void createCategory(GoodsCategory goodsCategory) {
        GoodsCategoryPo goodsCategoryPo = ObjectConversion.goodsCategory2GoodsCategoryPo(goodsCategory);
        goodsCategoryMapper.createCategory(goodsCategoryPo);
    }

    public void updateCategory(GoodsCategory goodsCategory) {
        GoodsCategoryPo goodsCategoryPo = ObjectConversion.goodsCategory2GoodsCategoryPo(goodsCategory);
        // TODO cannot set gmt_modified field
        goodsCategoryMapper.updateCategory(goodsCategoryPo);
    }

    public void deleteCategory(Integer id) {
        goodsCategoryMapper.deleteCategory(id);
    }


}
