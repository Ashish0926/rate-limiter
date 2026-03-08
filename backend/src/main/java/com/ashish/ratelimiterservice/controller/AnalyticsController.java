package com.ashish.ratelimiterservice.controller;

import com.ashish.ratelimiterservice.dto.MetricResponse;
import com.ashish.ratelimiterservice.service.AnalyticsService;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/analytics")
public class AnalyticsController {

  private final AnalyticsService analyticsService;

  public AnalyticsController(AnalyticsService analyticsService) {
    this.analyticsService = analyticsService;
  }

  @GetMapping("/request-rate")
  public MetricResponse requestRate() {
    return analyticsService.getRequestsCurrentMinute();
  }

  @GetMapping("/blocked-rate")
  public MetricResponse blockedRate() {
    return analyticsService.getBlockedCurrentMinute();
  }

  @GetMapping("/top-users")
  public Set<String> topUsers() {
    return analyticsService.getTopUsers();
  }

  @GetMapping("/top-endpoints")
  public Set<String> topEndpoints() {
    return analyticsService.getTopEndpoints();
  }
}
