package com.chrosciu.shop.payments;

import lombok.SneakyThrows;
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
    @Before(value = "@annotation(logPayments) && args(paymentRequest)")
    public void beforePayment(PaymentRequest paymentRequest, LogPayments logPayments) {
        log.info("New payment request: {}", paymentRequest);
        if (paymentRequest.getMoney().isLessThan(logPayments.threshold())) {
            throw new IllegalArgumentException("Biednych nie obslugujemy :)");
        }
    }

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void afterPaymentReturn(Payment payment) {
        log.info("A new payment of {} has been initiated", payment);
    }

    @AfterThrowing(value = "@annotation(LogPayments)", throwing = "throwable")
    public void afterPaymentException(Throwable throwable) {
        log.warn("Payment exception: ", throwable);
    }

    @After(value = "@annotation(LogPayments)")
    public void afterPayment() {
        log.info("After payment");
    }

    @Around(value = "@annotation(logPayments)")
    @SneakyThrows
    public Object aroundPayment(ProceedingJoinPoint pjp, LogPayments logPayments) {
        var arg = pjp.getArgs()[0];
        var paymentRequest = (PaymentRequest)arg;
        if (paymentRequest.getMoney().isLessThan(logPayments.threshold())) {
            log.info("Dla biednych nie pracujemy");
            return Payment.builder().status(PaymentStatus.FAILED).build();
        }
        log.info("Before real method");
        var object = pjp.proceed();
        log.info("After real method");
        return object;
    }
}
