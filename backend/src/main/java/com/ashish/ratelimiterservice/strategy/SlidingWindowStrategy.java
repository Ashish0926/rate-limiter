package com.ashish.ratelimiterservice.strategy;

import com.ashish.ratelimiterservice.service.RedisLuaExecutorService;
import org.springframework.stereotype.Component;

@Component("slidingWindow")
public class SlidingWindowStrategy implements RateLimitingStrategy {

  private final RedisLuaExecutorService redisLuaExecutorService;

  public SlidingWindowStrategy(RedisLuaExecutorService redisLuaExecutorService) {
    this.redisLuaExecutorService = redisLuaExecutorService;
  }

  @Override
  public boolean allowRequest(String apiKey, int limit) {
    Long requestCount = redisLuaExecutorService.execute(apiKey, 60);
    return requestCount.intValue() < limit;
  }
}
