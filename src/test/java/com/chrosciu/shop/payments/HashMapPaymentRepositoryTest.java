package com.chrosciu.shop.payments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashMapPaymentRepositoryTest {

    private static final String PAYMENT_ID = "1";
    private static final Payment PAYMENT = Payment.builder()
            .id(PAYMENT_ID)
            .build();

    private final HashMapPaymentRepository paymentRepository = new HashMapPaymentRepository();

    @DisplayName("Should store payment under the payment id key")
    @Test
    void shouldStorePaymentUnderThePaymentIdKey() {
        //when
        paymentRepository.save(PAYMENT);

        //then
        assertEquals(PAYMENT, paymentRepository.getById(PAYMENT_ID));
    }

}
