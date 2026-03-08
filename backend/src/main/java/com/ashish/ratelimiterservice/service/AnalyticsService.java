package com.ashish.ratelimiterservice.service;

import com.ashish.ratelimiterservice.dto.MetricResponse;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

  private final StringRedisTemplate redisTemplate;

  public MetricResponse getRequestsCurrentMinute() {
    String minute = String.valueOf(System.currentTimeMillis() / 60000);

    String value = redisTemplate.opsForValue().get("analytics:requests:" + minute);

    return new MetricResponse("requests_per_minute", value);
  }

  public MetricResponse getBlockedCurrentMinute() {
    String minute = String.valueOf(System.currentTimeMillis() / 60000);

    String value = redisTemplate.opsForValue().get("analytics:blocked:" + minute);

    return new MetricResponse("blocked_per_minute", value);
  }

  public Set<String> getTopUsers() {
    return redisTemplate.opsForZSet().reverseRange("analytics:userRanking", 0, 4);
  }

  public Set<String> getTopEndpoints() {
    return redisTemplate.opsForZSet().reverseRange("analytics:endpointRanking", 0, 4);
  }
}
