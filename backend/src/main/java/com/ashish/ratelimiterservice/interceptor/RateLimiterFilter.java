package com.ashish.ratelimiterservice.interceptor;

import static com.ashish.ratelimiterservice.enums.StrategyType.SLIDING_WINDOW;

import com.ashish.ratelimiterservice.messaging.event.ApiRequestEvent;
import com.ashish.ratelimiterservice.messaging.producer.AnalyticsProducer;
import com.ashish.ratelimiterservice.service.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class RateLimiterFilter extends OncePerRequestFilter {

  private final RateLimiterService rateLimiterService;
  private final AnalyticsProducer analyticsProducer;

  public RateLimiterFilter(
      RateLimiterService rateLimiterService, AnalyticsProducer analyticsProducer) {
    this.rateLimiterService = rateLimiterService;
    this.analyticsProducer = analyticsProducer;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    long startTime = System.currentTimeMillis();
    String apiKey = request.getHeader("X-API-KEY");
    if (apiKey != null) {
      log.info("X-API-KEY header is present, forwarding to RateLimiterService");
      boolean allowed = rateLimiterService.allow(SLIDING_WINDOW.getName(), apiKey, 5);
      long latency = System.currentTimeMillis() - startTime;

      analyticsProducer.send(
          new ApiRequestEvent(
              apiKey, request.getRequestURI(), allowed, System.currentTimeMillis(), latency));

      if (!allowed) {
        response.setStatus(429);
        response.getWriter().write("Rate limit exceeded");
        return;
      }
    }
    filterChain.doFilter(request, response);
  }
}
