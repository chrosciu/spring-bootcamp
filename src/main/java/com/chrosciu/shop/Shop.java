package com.chrosciu.shop;

import com.chrosciu.shop.payments.LoggingPaymentService;
import com.chrosciu.shop.payments.PaymentRequest;
import com.chrosciu.shop.payments.PaymentService;
import com.chrosciu.shop.payments.PolishMoney;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Shop {
    private static final String BASE_PACKAGE = "com.chrosciu.shop";

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
            PaymentService paymentService = applicationContext.getBean(LoggingPaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(PolishMoney.of(100))
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info("{}", payment);
        }
    }
}
