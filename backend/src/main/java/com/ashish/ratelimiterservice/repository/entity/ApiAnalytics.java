package com.ashish.ratelimiterservice.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "api_analytics")
public class ApiAnalytics {

  @Id @GeneratedValue private UUID id;

  private String apiKey;
  private String endpoint;
  private boolean allowed;
  private long latency;
  private long timestamp;
}
