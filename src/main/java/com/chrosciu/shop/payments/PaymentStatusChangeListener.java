package com.chrosciu.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public class PaymentStatusChangeListener {
    @EventListener
    public void onPaymentStatusChange(PaymentStatusChangedEvent event) {
        log.info("Payment changed status: {}", event.getPayment());
    }
}
