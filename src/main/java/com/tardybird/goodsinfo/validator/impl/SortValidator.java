package com.tardybird.goodsinfo.validator.impl;

import com.google.common.collect.Lists;
import com.tardybird.goodsinfo.validator.Sort;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author nick
 */
public class SortValidator implements ConstraintValidator<Sort, String> {
    private List<String> valueList;

    @Override
    public void initialize(Sort sort) {
        valueList = Lists.newArrayList();
        for (String val : sort.accepts()) {
            valueList.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(s.toUpperCase());
    }
}
