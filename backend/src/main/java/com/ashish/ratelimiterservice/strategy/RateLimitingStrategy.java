package com.ashish.ratelimiterservice.strategy;

import org.springframework.stereotype.Component;

@Component
public interface RateLimitingStrategy {

  boolean allowRequest(String apiKey, int limit);
}
