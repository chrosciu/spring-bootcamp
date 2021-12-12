package com.chrosciu.shop.payments;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncrementalPaymentIdGenerator implements PaymentIdGenerator {
    private static final String ID_FORMAT = "%010d";

    private long index;

    @Override
    public String getNext() {
        return String.format(ID_FORMAT, ++index);
    }
}
