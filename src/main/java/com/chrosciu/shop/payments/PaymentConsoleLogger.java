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
    @Before(value = "@annotation(LogPayments) && args(paymentRequest)")
    public void beforePayment(PaymentRequest paymentRequest) {
        log.info("New payment request: {}", paymentRequest);
    }

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info("A new payment of {} has been initiated", payment);
    }

    @AfterThrowing(value = "@annotation(LogPayments)", throwing = "exception")
    public void onPaymentException(Exception exception) {
        log.info("Exception on payment: {}", exception.getClass().getSimpleName());
    }

    @After(value = "@annotation(LogPayments)")
    public void afterPayment() {
        log.info("After payment");
    }

    @Around(value = "@annotation(LogPayments) && args(paymentRequest)")
    @SneakyThrows
    public Object aroundPayment(ProceedingJoinPoint pjp, PaymentRequest paymentRequest) {

        log.info("Before payment - around");
        Payment result = null;
        if (paymentRequest.getMoney().isLessThan(1000)) {
            log.info("Biednych nie obslugujemy");
            result = Payment.builder().id("Z czapy").build();
        } else {
            result = (Payment)pjp.proceed();
        }
        log.info("After payment - around");
        return result;
    }



}

