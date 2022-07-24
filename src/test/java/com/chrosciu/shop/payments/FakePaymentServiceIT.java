package com.chrosciu.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class FakePaymentServiceIT {
    private static final String PAYMENT_ID = "1";
    private static final FastMoney MONEY = PolishMoney.of(1000);
    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();

    @Autowired
    private PaymentService paymentService;

    @Configuration
    @ComponentScan("com.chrosciu.shop")
    public static class SpringConfig {
    }


    @Test
    void shouldCopyMoneyToPayment() {
        var payment = paymentService.process(PAYMENT_REQUEST);
        assertThat(payment.getMoney()).isEqualTo(MONEY);
    }

}
