package com.chrosciu.shop.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public ApplicationEventListener applicationEventListener() {
        return new ApplicationEventListener();
    }

    @Bean
    public RetryMethodExecutor retryMethodExecutor() {
        return new RetryMethodExecutor();
    }
}
