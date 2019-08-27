package com.jinloes.metrics_api;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;

@EnableAutoConfiguration
@ComponentScan
public class MetricsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetricsApiApplication.class, args);
    }

    @Bean
    public MeterRegistry meterRegistry() {
        InfluxConfig config = new InfluxConfig() {
            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String db() {
                return "test";
            }

            @Override
            public String get(String k) {
                return null; // accept the rest of the defaults
            }

            @Override
            public String uri() {
                return "http://localhost:8086";
            }
        };
        return new InfluxMeterRegistry(config, Clock.SYSTEM);
    }
}
