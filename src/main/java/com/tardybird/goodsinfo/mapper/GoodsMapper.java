package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.po.GoodsPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DIX
 * @date 2019/12/4 16:44
 */

@Repository
public interface GoodsMapper {


    /**
     * 获得所有商品
     *
     * @return x
     */
    List<GoodsPo> getAllGoods();


    /**
     * x
     *
     * @return x
     */
    List<GoodsPo> findNewAndHotGoods();

    /**
     * xx
     *
     * @param goodsSn x
     * @param name    x
     * @return x
     */
    List<GoodsPo> getGoodsByCondition(String goodsSn, String name);


    /**
     * 通过商品id查找商品
     *
     * @param id
     * @return goods
     */
    GoodsPo getGoodsById(@Param("id") Integer id);

    List<GoodsPo> findGoodsByBrandId(@Param("brandId") String brandId);

    List<GoodsPo> findGoodsByGoodsCategoryId(@Param("id") String id);

    /**
     * 更新商品信息
     *
     * @param goods x
     * @return x
     */
    Integer updateGoods(GoodsPo goods);

    Integer updateCategoryId(@Param("goodsCategoryId") Integer goodsCategoryId);

    /**
     * 新建一个商品
     *
     * @param goods x
     * @return x
     */
    Integer createGoods(GoodsPo goods);

    /**
     * 路基删除商品
     *
     * @param id x
     * @return x
     */
    Integer deleteGoods(@Param("id") Integer id);


    /**
     * 级联删除种类时把商品种类设空
     * @param cid
     * @return
     */
    Integer deleteWithCategory(@Param("cid") Integer cid);

}
