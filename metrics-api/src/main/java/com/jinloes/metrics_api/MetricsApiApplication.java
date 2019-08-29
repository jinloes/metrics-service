package com.jinloes.metrics_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.elasticsearch.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableAutoConfiguration
@ComponentScan
@EnableElasticsearchRepositories
public class MetricsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetricsApiApplication.class, args);
    }

    @Bean
    public MeterRegistry meterRegistry() {
        return new SimpleMeterRegistry();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .registerModule(new JavaTimeModule());
    }

    @Bean
    public EntityMapper getEntityMapper() {
        return new CustomEntityMapper(objectMapper());
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client, EntityMapper entityMapper) {
        return new ElasticsearchTemplate(client, entityMapper);
    }
}
