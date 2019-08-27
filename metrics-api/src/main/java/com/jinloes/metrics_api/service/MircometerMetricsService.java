package com.jinloes.metrics_api.service;

import com.jinloes.metrics_api.model.MetricTag;
import com.jinloes.metrics_api.model.MetricsRecordType;
import io.micrometer.core.instrument.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MircometerMetricsService {
    private final MeterRegistry registry;

    @Autowired
    public MircometerMetricsService(MeterRegistry registry) {
        this.registry = registry;
    }

    public void recordMetric(String name, MetricsRecordType type, Double value, List<MetricTag> metricTags) {
        List<Tag> tags = metricTags.stream()
                .map(tag -> Tag.of(tag.getName(), tag.getValue()))
                .collect(Collectors.toList());
        switch (type) {
            case GAUGE:
                Gauge gauge = Gauge.builder(name, () -> value)
                        .tags(tags)
                        .register(registry);
                break;
            case COUNTER:
                Counter counter = Counter.builder(name)
                        .tags(tags)
                        .register(registry);
                counter.increment(value);
                break;
            case TIMER:
                Timer timer = Timer.builder(name)
                        .tags(tags)
                        .register(registry);
                timer.record(value.longValue(), TimeUnit.MILLISECONDS);
            default:
                throw new UnsupportedOperationException(String.format("%s is not supported for recording metrics", type));
        }

    }

}
