package com.tardybird.goodsinfo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author nick
 */
@Aspect
@Component
public class ProductAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @After("execution(* com.tardybird.goodsinfo.dao.ProductDao.deductProductStock(..)))")
    public void deductProductStock(JoinPoint joinPoint) {

        logger.info("After deduct stock..." +
                joinPoint.getTarget().getClass() +
                ", args=" + Arrays.asList(joinPoint.getArgs()) +
                ", method=" + joinPoint.getSignature());
    }
}
