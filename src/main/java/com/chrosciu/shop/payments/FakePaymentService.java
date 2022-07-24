package com.chrosciu.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    @Override
    @LogPayments
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        return paymentRepository.save(payment);
    }
}
