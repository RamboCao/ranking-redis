package com.rank;

import com.rank.config.JerseyServiceAutoScanner;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Caolp
 */
@SpringBootApplication(scanBasePackages = "com.rank")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = false, exposeProxy = true)
public class RankRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RankRedisApplication.class, args);
    }

    @Bean
    public ResourceConfig jerseyConfig(ApplicationContext applicationContext) {
        var config = new ResourceConfig();
        config.registerClasses(JerseyServiceAutoScanner.getPublishJerseyServiceClasses(applicationContext, "com.rank.service"));
        return config;
    }

}
