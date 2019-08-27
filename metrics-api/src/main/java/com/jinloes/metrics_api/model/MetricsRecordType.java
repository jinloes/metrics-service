package com.jinloes.metrics_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MetricsRecordType {
    COUNTER, GAUGE, TIMER;

    @JsonCreator
    public static MetricsRecordType fromString(String str) {
        return valueOf(str.toUpperCase());
    }
}
