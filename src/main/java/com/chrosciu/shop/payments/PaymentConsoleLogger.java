package com.chrosciu.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class PaymentConsoleLogger {
    private static final String LOG_FORMAT = "A new payment of {} has been initiated";

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info(LOG_FORMAT, payment);
    }

    @Before(value = "@annotation(LogPayments) && args(paymentRequest)")
    public void beforePayment(PaymentRequest paymentRequest) {
        log.info("New payment request: {}", paymentRequest);
    }

    @After(value = "@annotation(LogPayments)")
    public void log() {
        log.info("After payment");
    }

    @AfterThrowing(value = "@annotation(LogPayments)", throwing = "exception")
    public void onException(Exception exception) {
        log.info("Payment exception: {}", exception.getClass().getSimpleName());
    }

    @Around(value = "@annotation(LogPayments)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Before around");
        var result = proceedingJoinPoint.proceed();
        log.info("After around");
        return result;
    }


}
