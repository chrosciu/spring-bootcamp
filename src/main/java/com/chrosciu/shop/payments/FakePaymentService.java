package com.chrosciu.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Instant;

@Slf4j
@Service("paymentService")
public class FakePaymentService implements PaymentService {
    @Autowired
    @Qualifier("uuid")
    private PaymentIdGenerator paymentIdGenerator;
    @Autowired
    private PaymentRepository paymentRepository;

    public FakePaymentService() {
        log.info("{}", paymentIdGenerator);
        log.info("{}", paymentRepository);
    }

    @PostConstruct
    private void init() {
        log.info("Payment service initialized");
        log.info("{}", paymentIdGenerator);
        log.info("{}", paymentRepository);
    }

    @PreDestroy
    private void destroy() {
        log.info("Payment service is going down");
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
