package com.chrosciu.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Random;

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
    @Retry(attempts = 3)
    public Payment process(PaymentRequest paymentRequest) {
        var payment =  Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        log.info("Before random");
        var random = new Random().nextBoolean();
        if (random) {
            throw new RuntimeException("blah");
        }
        log.info("After random");
        return paymentRepository.save(payment);
    }
}
