package com.lance.spike.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisManager
 * @Description TODO Redis缓存管理
 * @Author 陆逸飞
 * @Date 2018-12-20 11:06
 * @Version v1.0
 * NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
 */
@Component
public final class RedisManager {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(RedisManager.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofMinutes(20));
    }

    public void setValue(String key, String value, long minutes) {
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofMinutes(minutes));
    }

    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}

