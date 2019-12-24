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
     * 查找新的和热的商品
     *
     * @return 商品
     */
    List<GoodsPo> findNewAndHotGoods();

    /**
     * 按照条件查询商品
     *
     * @param goodsSn 商品条形码
     * @param name    商品名称
     * @return 商品
     */
    List<GoodsPo> getGoodsByCondition(String goodsSn, String name);


    /**
     * 通过商品id查找商品
     *
     * @param id 商品ID
     * @return 商品
     */
    GoodsPo getGoodsByIdAdmin(@Param("id") Integer id);

    /**
     * 用户通过商品id查找商品(不可查找到未上架的)
     *
     * @param id 商品ID
     * @return 商品
     */
    GoodsPo getGoodsByIdUser(@Param("id") Integer id);

    /**
     *通过品牌ID查询商品信息
     * @param id 品牌ID
     * @return 商品信息
     */
    List<GoodsPo> findGoodsByBrandId(@Param("id") Integer id);

    /**
     *通过种类ID查询商品信息
     * @param id 种类ID
     * @param page 页数
     * @param limit 每页大小
     * @return 商品信息
     */
    List<Integer> findGoodsIdsByCategoryId(@Param("id") Integer id, @Param("page") Integer page,
                                           @Param("limit") Integer limit);

    /**
     * 更新商品信息
     *
     * @param goods 商品信息
     * @return 改变行数
     */
    Integer updateGoods(GoodsPo goods);

    /**
     * 修改分类信息
     * @param goodsCategoryId 新的分类ID
     * @return 改变行数
     */
    Integer updateCategoryId(@Param("goodsCategoryId") Integer goodsCategoryId);

    /**
     * 新建一个商品
     *
     * @param goods 商品
     * @return 改变行数
     */
    Integer createGoods(GoodsPo goods);

    /**
     * 删除商品
     *
     * @param id 商品
     * @return 改变行数
     */
    Integer deleteGoods(@Param("id") Integer id);

    /**
     * 判断商品是否在售
     * @param id 商品
     * @return true 在售
     */
    Integer selectOnSaleGoods(@Param("id") Integer id);

}
