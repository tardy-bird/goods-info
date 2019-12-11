package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return goodsCategoryMapper.getAllCategories();
    }

    public List<GoodsCategory> getLevelOneCategories() {
        return goodsCategoryMapper.getLevelOneCategories();
    }

    public GoodsCategory getCategory(Integer id) {
        return goodsCategoryMapper.getCategory(id);
    }

    public List<GoodsCategory> getLevelTwoByPid(Integer pid) {
        return goodsCategoryMapper.getLevelTwoByPid(pid);
    }

    public void createCategory(GoodsCategory goodsCategory) {
        goodsCategoryMapper.createCategory(goodsCategory);
    }

    public void updateCategory(GoodsCategory goodsCategory) {
        // TODO cannot set gmt_modified field
        goodsCategoryMapper.updateCategory(goodsCategory);
    }

    public void deleteCategory(Integer id) {
        goodsCategoryMapper.deleteCategory(id);
    }


}
