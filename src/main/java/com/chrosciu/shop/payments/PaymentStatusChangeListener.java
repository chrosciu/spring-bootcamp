package com.chrosciu.shop.payments;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class PaymentStatusChangeListener {
    @EventListener
    @SneakyThrows
    @Async("asyncExecutor")
    public void onPaymentStatusChange(PaymentStatusChangedEvent event) {
        log.info("Payment changed status: {}", event.getPayment());
        Thread.sleep(2000);
    }
}
