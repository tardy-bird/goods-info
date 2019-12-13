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
     * @return
     */
    List<GoodsPo> getAllGoods();

    /**
     * （用户）默认获得热卖商品列表（只有id和pic）
     *
     * @return x
     */
    List<GoodsPo> findHotGoods();


    /**
     * x
     *
     * @return x
     */
    List<GoodsPo> findNewGoods();

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

    /**
     * 更新商品信息
     *
     * @param goods x
     * @return x
     */
    int updateGoods(GoodsPo goods);

    /**
     * 新建一个商品
     *
     * @param goods x
     * @return x
     */
    int createGoods(GoodsPo goods);

    /**
     * 路基删除商品
     * @param id x
     * @return x
     */
    int deleteGoods(@Param("id") Integer id);

    Integer selectOnSaleGoods();


}
