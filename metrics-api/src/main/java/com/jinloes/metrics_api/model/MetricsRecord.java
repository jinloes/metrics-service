package com.jinloes.metrics_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MetricsRecord {
    private final String name;
    private final MetricsRecordType type;
    private final Double value;
    private final List<MetricTag> tags;

    @JsonCreator
    public MetricsRecord(@JsonProperty("name") String name, @JsonProperty("type") MetricsRecordType type,
                         @JsonProperty("value") Double value,
                         @JsonProperty("tags") List<MetricTag> tags) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.tags = tags;
    }
}
