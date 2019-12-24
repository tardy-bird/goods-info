package com.tardybird.goodsinfo.domain;

import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.po.GoodsPo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 二级目录对象
 *
 * @author 数据库与对象模型标准组
 * @date Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GoodsCategory extends GoodsCategoryPo {
    private List<GoodsPo> goodsPoList;
}
