package com.chrosciu.shop.payments;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAspectJAutoProxy
@EnableAsync
public class PaymentsConfiguration {
    @Bean("paymentIdGenerator")
    public PaymentIdGenerator incrementalPaymentIdGenerator() {
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new HashMapPaymentRepository();
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger() {
        return new PaymentConsoleLogger();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PaymentService fakePaymentService(
            PaymentIdGenerator paymentIdGenerator,
            PaymentRepository paymentRepository,
            ApplicationEventPublisher eventPublisher) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
    }

    @Bean
    public PaymentStatusChangeListener paymentStatusChangeListener() {
        return new PaymentStatusChangeListener();
    }

    @Bean
    public TaskExecutor getAsyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(100);
        executor.initialize();
        return executor;
    }
}
