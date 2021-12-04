package com.chrosciu.shop.payments;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@IdGenerator("uuid")
public class UUIDPaymentIdGenerator implements PaymentIdGenerator {
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
