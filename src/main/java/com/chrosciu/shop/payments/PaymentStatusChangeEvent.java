package com.chrosciu.shop.payments;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PaymentStatusChangeEvent extends ApplicationEvent {
    @Getter
    private final Payment payment;

    public PaymentStatusChangeEvent(Object source, Payment payment) {
        super(source);
        this.payment = payment;
    }
}
