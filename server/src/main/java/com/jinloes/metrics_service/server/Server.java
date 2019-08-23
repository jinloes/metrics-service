package com.jinloes.metrics_service.server;

import com.google.common.collect.Lists;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@RestController
@EnableAutoConfiguration
public class Server {
    @Autowired
    private MeterRegistry meterRegistry;

    @RequestMapping("/")
    public String home() {
        List<String> list = Lists.newArrayList("test1", "test2");
        Random randomizer = new Random();
        String random = list.get(randomizer.nextInt(list.size()));
        Timer timer = meterRegistry.timer("my.timer", Tags.of("region", random));
        return timer.record(() -> "Hello World!");
    }

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
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