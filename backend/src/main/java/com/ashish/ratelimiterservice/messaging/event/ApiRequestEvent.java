package com.ashish.ratelimiterservice.messaging.event;

public record ApiRequestEvent(
    String apiKey, String endpoint, boolean allowed, long timestamp, long latency) {}
