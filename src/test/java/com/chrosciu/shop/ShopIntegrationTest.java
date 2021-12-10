package com.chrosciu.shop;

import com.chrosciu.shop.payments.PaymentRequest;
import com.chrosciu.shop.payments.PaymentService;
import com.chrosciu.shop.payments.PaymentStatus;
import com.chrosciu.shop.payments.PaymentStatusChangedEvent;
import com.chrosciu.shop.payments.PolishMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ShopTestConfig.class)
@RecordApplicationEvents
public class ShopIntegrationTest {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ApplicationEvents applicationEvents;

    @Test
    public void shouldLoadPaymentService() {
        Assertions.assertNotNull(paymentService);
    }

    @Test
    public void shouldProcessPayment() {
        //given
        var paymentRequest = PaymentRequest.builder()
                .money(PolishMoney.of(100))
                .build();
        //when
        var payment = paymentService.process(paymentRequest);
        //then
        Assertions.assertNotNull(payment.getId());
        Assertions.assertEquals(PaymentStatus.STARTED, payment.getStatus());

        var paymentStatusChangedEvents = applicationEvents.stream()
                .filter(applicationEvent -> applicationEvent instanceof PaymentStatusChangedEvent)
                .map(applicationEvent -> (PaymentStatusChangedEvent)applicationEvent)
                .collect(Collectors.toList());
        Assertions.assertEquals(1, paymentStatusChangedEvents.size());
        Assertions.assertEquals(payment, paymentStatusChangedEvents.get(0).getPayment());
    }
}
