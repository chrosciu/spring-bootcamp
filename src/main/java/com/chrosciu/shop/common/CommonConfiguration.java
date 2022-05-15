package com.chrosciu.shop.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public RetryMethodExecutor retryMethodExecutor() {
        return new RetryMethodExecutor();
    }

    @Bean
    public ContextListener contextListener() {
        return new ContextListener();
    }
}
