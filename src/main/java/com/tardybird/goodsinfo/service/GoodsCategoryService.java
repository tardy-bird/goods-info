package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.domain.Goods;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.mapper.GoodsCategoryMapper;
import com.tardybird.goodsinfo.mapper.GoodsMapper;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.util.ObjectConversion;
import com.tardybird.goodsinfo.util.converter.Converter;
import com.tardybird.goodsinfo.util.converter.GoodsCategoryConverter;
import com.tardybird.goodsinfo.util.converter.GoodsConverter;
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

    final GoodsCategoryMapper goodsCategoryMapper;
    final GoodsMapper goodsMapper;

    private Converter<GoodsCategoryPo, GoodsCategory> converter = new GoodsCategoryConverter();

    public GoodsCategoryService(GoodsCategoryMapper goodsCategoryMapper, GoodsMapper goodsMapper) {
        this.goodsCategoryMapper = goodsCategoryMapper;
        this.goodsMapper = goodsMapper;
    }

    public List<GoodsCategoryPo> getAllCategories(Integer page, Integer limit) {
        return goodsCategoryMapper.getAllCategories(page, limit);
    }

    public List<GoodsCategory> getLevelOneCategories() {
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryMapper.getLevelOneCategories();
        return getGoodsCategories(goodsCategoryPos);
    }

    public GoodsCategoryPo getCategory(Integer id) {
        return goodsCategoryMapper.getCategory(id);
    }

    public List<GoodsCategoryPo> getLevelTwoByPid(Integer pid) {

        return goodsCategoryMapper.getLevelTwoByPid(pid);
    }

    private List<GoodsCategory> getGoodsCategories(List<GoodsCategoryPo> goodsCategoryPos) {
        List<GoodsCategory> goodsCategoryList = new ArrayList<>();

        for (GoodsCategoryPo goodsCategoryPo : goodsCategoryPos) {
            GoodsCategory goodsCategory = converter.converterFromPo(goodsCategoryPo);

            goodsCategory.setGoodsPoList(null);

            goodsCategoryList.add(goodsCategory);
        }
        return goodsCategoryList;
    }

    public GoodsCategoryPo createCategory(GoodsCategoryPo goodsCategory) {
        Integer affectedRows = goodsCategoryMapper.createCategory(goodsCategory);
        if (affectedRows <= 0) {
            return null;
        }
        return goodsCategory;
    }

    public Boolean updateCategory(GoodsCategoryPo goodsCategory) {
        Integer affectedRows = goodsCategoryMapper.updateCategory(goodsCategory);
        return affectedRows > 0;
    }

    public Boolean deleteCategory(Integer id) {
        GoodsCategoryPo goodsCategoryPo = goodsCategoryMapper.getCategory(id);
        if (goodsCategoryPo == null) {
            return false;
        }

        // 一级分类
        if (goodsCategoryPo.getPid() == 0) {
            Integer rows = goodsCategoryMapper.deleteL2withL1(goodsCategoryPo.getId());
            if (rows <= 0) {
                return false;
            }
            List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryMapper.getLevelTwoByPid(id);

            for (GoodsCategoryPo categoryPo : goodsCategoryPos) {
                Integer goodsCategoryId = categoryPo.getId();
                // 更新相关商品的种类
                rows = goodsMapper.updateCategoryId(goodsCategoryId);
                if (rows <= 0) {
                    return false;
                }
            }
        }

        Integer affectedRows = goodsCategoryMapper.deleteCategory(id);
        return affectedRows > 0;
    }

    public boolean updateParentCategory(GoodsCategoryPo goodsCategoryPo) {
        Integer affectedRows = goodsCategoryMapper.updateParentCategory(goodsCategoryPo);
        return affectedRows > 0;
    }
}
