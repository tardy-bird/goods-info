package com.tardybird.goodsinfo.validator.impl;

import com.google.common.collect.Lists;
import com.tardybird.goodsinfo.validator.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author nick
 */
public class OrderValidator implements ConstraintValidator<Order, String> {
    private List<String> valueList;

    @Override
    public void initialize(Order order) {
        valueList = Lists.newArrayList();
        for (String val : order.accepts()) {
            valueList.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(s.toUpperCase());
    }
}
