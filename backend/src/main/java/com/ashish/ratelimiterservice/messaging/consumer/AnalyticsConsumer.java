package com.ashish.ratelimiterservice.messaging.consumer;

import com.ashish.ratelimiterservice.messaging.event.ApiRequestEvent;
import com.ashish.ratelimiterservice.service.AnalyticsAggregator;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class AnalyticsConsumer {

  private final AnalyticsAggregator aggregator;
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = "rate-limiter.api-analytics",
      groupId = "group-id",
      properties = "value.deserializer=${spring.kafka.consumer.value-deserializer}")
  public void consume(ConsumerRecord<String, String> record) {
    // store analytics
    System.out.println("Received " + record.value());
    ApiRequestEvent event = objectMapper.readValue(record.value(), ApiRequestEvent.class);
    aggregator.aggregate(event);
  }
}
