package com.jinloes.metrics_api.web;

import com.jinloes.metrics_api.model.MetricsRecord;
import com.jinloes.metrics_api.service.MircometerMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    private final MircometerMetricsService metricsService;

    @Autowired
    public MetricsController(MircometerMetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @PostMapping("/metrics")
    public ResponseEntity<?> recordMetrics(@RequestBody MetricsRecord record) {
        metricsService.recordMetric(record.getName(), record.getType(), record.getValue(), record.getTags());
        return ResponseEntity.noContent().build();
    }
}
