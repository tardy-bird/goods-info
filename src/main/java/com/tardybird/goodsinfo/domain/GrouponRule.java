package com.tardybird.goodsinfo.domain;

import com.tardybird.goodsinfo.po.GrouponRulePo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * 团购规则对象
 *
 * @author 数据库与对象模型标准组
 * @date Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GrouponRule extends GrouponRulePo {
    private List<Strategy> strategyList;

    @Getter
    @Setter
    private static class Strategy {
        private Integer lowerBound;
        private Integer upperBound;
        private BigDecimal discountRate;
    }
}
