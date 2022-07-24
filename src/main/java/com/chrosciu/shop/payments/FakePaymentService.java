package com.chrosciu.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
public class FakePaymentService {
    private final PaymentIdGenerator paymentIdGenerator;

    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        log.info("A new payment of {} has been initiated", payment.getMoney());
        return payment;
    }
}
