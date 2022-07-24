package com.chrosciu.shop.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAspectJAutoProxy
@EnableAsync
@PropertySource("classpath:payments.properties")
public class PaymentsConfiguration {
    @Bean
    @Profile("!uuid")
    public PaymentIdGenerator incrementalPaymentIdGenerator(@Value("${generator.initial:10}") long initialValue) {
        return new IncrementalPaymentIdGenerator(initialValue);
    }

    @Bean
    @Profile("uuid")
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
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator,
                                         PaymentRepository paymentRepository,
                                         ApplicationEventPublisher eventPublisher) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
    }

    @Bean
    public PaymentStatusChangeListener paymentStatusChangeListener() {
        return new PaymentStatusChangeListener();
    }

    @Bean
    public Executor asyncExecutor() {
        return Executors.newFixedThreadPool(5);
    }


}
