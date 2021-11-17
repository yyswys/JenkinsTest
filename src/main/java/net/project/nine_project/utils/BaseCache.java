package net.project.nine_project.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class BaseCache {

    private Cache<String,Object> tenMinCache = CacheBuilder.newBuilder()
            //设置初始缓存大小，后续会扩容
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数
            .concurrencyLevel(5)
            //缓存过期时间10分钟
            .expireAfterWrite(600, TimeUnit.SECONDS)
            //缓存命中率
            .recordStats()
            .build();

    public Cache<String, Object> getTenMinCache() {
        return tenMinCache;
    }

    public void setTenMinCache(Cache<String, Object> tenMinCache) {
        this.tenMinCache = tenMinCache;
    }
}