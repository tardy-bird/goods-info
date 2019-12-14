package com.tardybird.goodsinfo.task;

import com.tardybird.goodsinfo.dao.GoodsDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CachedObjectsScheduler {

    final GoodsDao goodsDao;

    public CachedObjectsScheduler(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Scheduled(cron = "0 0/30 * * * ? ")
    private void handle() {
        goodsDao.storeHotAndNewObjects();
    }
}
