package com.tardybird.goodsinfo.task;

import com.tardybird.goodsinfo.dao.GoodsDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CachedObjectsScheduler {

    final GoodsDao goodsDao;

    public CachedObjectsScheduler(GoodsDao goodsDao, RedisTemplate<String, List<String>> redisTemplateOfString) {
        this.goodsDao = goodsDao;
    }

    @Scheduled(cron = "0 0/30 * * * ? ")
    private void handle() {
        goodsDao.storeHotAndNewObjects();
    }
}
