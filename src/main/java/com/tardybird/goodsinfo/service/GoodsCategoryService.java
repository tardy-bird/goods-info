package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DIX
 * @version 1.0
 * @description
 * @date 2019/12/5 21:20
 */
@Service
public class GoodsCategoryService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    public List<GoodsCategory> getAllCategory(){return goodsCategoryMapper.getAllCategories();}

    public List<GoodsCategory> getLevelOneCategories(){return goodsCategoryMapper.getLevelOneCategories();}

    public GoodsCategory getCategory(Integer id){return goodsCategoryMapper.getCategory(id);}

    public List<GoodsCategory> getLevelTwoByPid(Integer pid){return goodsCategoryMapper.getLevelTwoByPid(pid);}

    public int createCategory(GoodsCategory goodsCategory){return goodsCategoryMapper.createCategory(goodsCategory);}

    public int updateCategory(GoodsCategory goodsCategory){return goodsCategoryMapper.updateCategory(goodsCategory);}

    public int deleteCategory(Integer id){return goodsCategoryMapper.deleteCategory(id);}


}
