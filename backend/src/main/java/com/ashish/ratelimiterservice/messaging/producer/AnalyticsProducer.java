package com.ashish.ratelimiterservice.messaging.producer;

import com.ashish.ratelimiterservice.messaging.event.ApiRequestEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsProducer {

  private final KafkaTemplate<String, ApiRequestEvent> kafkaTemplate;

  public AnalyticsProducer(KafkaTemplate<String, ApiRequestEvent> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void send(ApiRequestEvent event) {
    kafkaTemplate.send("rate-limiter.api-analytics", event);
  }
}
