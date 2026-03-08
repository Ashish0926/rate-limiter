package com.ashish.ratelimiterservice.service;

import java.util.List;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

@Service
public class RedisLuaExecutorService {

  private final StringRedisTemplate redisTemplate;
  private final DefaultRedisScript<Long> script;

  public RedisLuaExecutorService(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
    script = new DefaultRedisScript<>();
    script.setResultType(Long.class);
    script.setScriptText(
        "local current = redis.call('INCR', KEYS[1]) "
            + "if tonumber(current) == 1 then "
            + "redis.call('EXPIRE', KEYS[1], ARGV[1]) end "
            + "return current");
  }

  public Long execute(String key, long expirySeconds) {
    return redisTemplate.execute(script, List.of(key), String.valueOf(expirySeconds));
  }
}
