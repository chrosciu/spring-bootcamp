package com.chrosciu.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class LoggingPaymentService implements PaymentService {
    private final PaymentService paymentService;

    private static final String LOG_FORMAT = "A new payment of {} has been initiated";

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = paymentService.process(paymentRequest);
        log.info(LOG_FORMAT, payment);
        return payment;
    }
}
