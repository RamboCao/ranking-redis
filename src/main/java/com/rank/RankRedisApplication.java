package com.rank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Caolp
 */
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = false, exposeProxy = true)
public class RankRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RankRedisApplication.class, args);
    }

}
