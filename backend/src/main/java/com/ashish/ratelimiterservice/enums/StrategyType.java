package com.ashish.ratelimiterservice.enums;

import lombok.Getter;

@Getter
public enum StrategyType {
  SLIDING_WINDOW("slidingWindow"),
  TOKEN_BUCKET("tokenBucket");

  private final String name;

  StrategyType(String name) {
    this.name = name;
  }
}
