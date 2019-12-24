package com.tardybird.goodsinfo.task;

import com.tardybird.goodsinfo.dao.GoodsDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nick
 */
@Component
public class CachedObjectsScheduler {

    final GoodsDao goodsDao;

    public CachedObjectsScheduler(GoodsDao goodsDao, RedisTemplate<String, List<String>> redisTemplateOfString) {
        this.goodsDao = goodsDao;
    }

    /**
     * 定时任务，每半个小时刷新一下Redis中的新品和热品
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    private void handle() {
        goodsDao.storeHotAndNewObjects();
    }
}
