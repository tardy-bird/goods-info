package com.tardybird.goodsinfo.mapper;

import com.tardybird.goodsinfo.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DIX
 * @date 2019/12/4 16:44
 */


public interface GoodsMapper {

    /**
     * 通过goodsSn查找商品
     * @param goodsSn
     * @return goods
     */
    Goods getGoodsByGoodsSn(@Param("goodsSn") String goodsSn);

    /***
     * 通过商品名字查找商品
     * @param name
     * @return goods
     */
    Goods getGoodsByName(@Param("name") String name);

    /**
     * 通过商品id查找商品
     * @param id
     * @return goods
     */
    Goods getGoodsById(@Param("id") Integer id);

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    int updateGoods(Goods goods);

    /**
     * 新建一个商品
     * @param goods
     * @return
     */
    int createGoods(Goods goods);

    /**
     * 逻辑删除商品
     * @return
     */
    int deleteGoods();


}
