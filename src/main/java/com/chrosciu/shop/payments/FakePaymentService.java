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
        var event = new PaymentStatusChangeEvent(this, payment);
        log.info("Before event");
        eventPublisher.publishEvent(event);
        log.info("After event");
        return paymentRepository.save(payment);
    }
}
