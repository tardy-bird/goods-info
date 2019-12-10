package com.tardybird.goodsinfo.service;

import com.tardybird.goodsinfo.dao.GoodsDao;
import com.tardybird.goodsinfo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    GoodsDao goodsDao;

    public List<Product> getProductByGoodsId(Integer id) {
        return goodsDao.getProductByGoodsId(id);
    }
}
