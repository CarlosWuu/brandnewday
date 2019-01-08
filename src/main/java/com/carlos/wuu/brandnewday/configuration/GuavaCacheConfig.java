package com.carlos.wuu.brandnewday.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GuavaCacheConfig {

    @Bean
    public CacheManager cacheManager(){

        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder().
                //设置过期时间
                        expireAfterAccess(10, TimeUnit.SECONDS).
                //设置最大数量键值对
                        maximumSize(1000);

        caffeineCacheManager.setCaffeine(caffeine);

        return caffeineCacheManager;
    }

}
