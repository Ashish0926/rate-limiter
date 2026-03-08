package com.ashish.ratelimiterservice.repository;

import com.ashish.ratelimiterservice.repository.entity.ApiAnalytics;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiAnalyticsRepository extends JpaRepository<ApiAnalytics, UUID> {}
