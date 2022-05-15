package com.chrosciu.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class PaymentConsoleLogger {
    private static final String LOG_FORMAT = "A new payment of {} has been initiated";

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info(LOG_FORMAT, payment);
    }
}

