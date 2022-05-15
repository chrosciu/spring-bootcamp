package com.chrosciu.shop.payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//@Primary
@Qualifier("uuid")
public class UUIDPaymentIdGenerator implements PaymentIdGenerator {
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
