package com.example.demo;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching  // 开启缓存
public class CacheConfig {
    // 使用默认配置即可，后续可以自定义
}