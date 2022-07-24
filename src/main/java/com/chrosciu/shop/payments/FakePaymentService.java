package com.chrosciu.shop.payments;

import com.chrosciu.shop.common.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @LogPayments(threshold = 1000)
    @Retry(attempts = 2)
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        if (0 == 1) {
            throw new RuntimeException("blah");
        }
        var event = new PaymentStatusChangedEvent(this, payment);
        log.info("Before sending event");
        eventPublisher.publishEvent(event);
        log.info("After sending event");
        return paymentRepository.save(payment);
    }

    private void init() {
        log.info("PaymentService has been initialized");
    }

    private void destroy() {
        log.info("PaymentService is to be destroyed");
    }
}
