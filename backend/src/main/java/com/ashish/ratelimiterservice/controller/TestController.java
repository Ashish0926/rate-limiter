package com.ashish.ratelimiterservice.controller;

import com.ashish.ratelimiterservice.service.RateLimiterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

  private final RateLimiterService rateLimiterService;

  public TestController(RateLimiterService rateLimiterService) {
    this.rateLimiterService = rateLimiterService;
  }

  @GetMapping("/hello")
  public String hello() {
    return "API response";
  }

  @GetMapping("/v1/hello")
  public String helloV1() {
    return "API V1 response";
  }
}
