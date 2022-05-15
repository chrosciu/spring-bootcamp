package com.chrosciu.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public class PaymentStatusChangeListener {

    @EventListener
    public void onPaymentStatusChange(PaymentStatusChangeEvent statusChangedEvent) {
        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
    }

}
