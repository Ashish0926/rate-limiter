package com.ashish.ratelimiterservice.service;

import com.ashish.ratelimiterservice.messaging.event.ApiRequestEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsAggregator {

  private final StringRedisTemplate redisTemplate;

  public AnalyticsAggregator(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void aggregate(ApiRequestEvent event) {

    String minute = String.valueOf(event.timestamp() / 60000);

    redisTemplate.opsForValue().increment("analytics:requests:" + minute);

    redisTemplate.opsForValue().increment("analytics:user:" + event.apiKey());

    redisTemplate.opsForValue().increment("analytics:endpoint:" + event.endpoint());

    redisTemplate.opsForZSet().incrementScore("analytics:userRanking", event.apiKey(), 1);

    redisTemplate.opsForZSet().incrementScore("analytics:endpointRanking", event.endpoint(), 1);

    if (!event.allowed()) {
      redisTemplate.opsForValue().increment("analytics:blocked:" + minute);
    }
  }
}
