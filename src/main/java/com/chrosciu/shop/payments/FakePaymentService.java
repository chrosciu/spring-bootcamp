package com.chrosciu.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    private void init() {
        log.info("Init");
    }

    private void destroy() {
        log.info("Destroy");
    }

    @Override
    @LogPayments
    public Payment process(PaymentRequest paymentRequest) {
        var payment =  Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        return paymentRepository.save(payment);
    }
}
