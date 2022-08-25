package com.rank.config;

import feign.Contract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caolp
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public Contract feignContract(){
        return new SpringMvcContract();
    }
}
