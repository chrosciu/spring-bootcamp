package com.chrosciu.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingPaymentService implements PaymentService {
    private static final String LOG_FORMAT = "A new payment of {} has been initiated";

    private final PaymentService paymentService;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = paymentService.process(paymentRequest);
        log.info(LOG_FORMAT, payment);
        return payment;
    }
}
