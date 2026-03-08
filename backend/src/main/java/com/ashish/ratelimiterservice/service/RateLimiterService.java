package com.ashish.ratelimiterservice.service;

import com.ashish.ratelimiterservice.exception.InvalidRequestException;
import com.ashish.ratelimiterservice.strategy.RateLimitingStrategy;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

  private final Map<String, RateLimitingStrategy> strategyMap;

  public RateLimiterService(Map<String, RateLimitingStrategy> strategyMap) {
    this.strategyMap = strategyMap;
  }

  public boolean allow(String strategyType, String apiKey, int limit) {
    RateLimitingStrategy rateLimitingStrategy = strategyMap.get(strategyType);
    if (rateLimitingStrategy == null) {
      throw new InvalidRequestException("Strategy type " + strategyType + " is not supported");
    }
    return rateLimitingStrategy.allowRequest(apiKey, limit);
  }
}
