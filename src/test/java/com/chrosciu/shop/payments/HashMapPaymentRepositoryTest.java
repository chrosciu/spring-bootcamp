package com.chrosciu.shop.payments;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapPaymentRepositoryTest {
    private static final String PAYMENT_ID = "1";
    private static final Payment PAYMENT = Payment.builder()
            .id(PAYMENT_ID)
            .build();

    private final HashMapPaymentRepository paymentRepository = new HashMapPaymentRepository();

    @Test
    void shouldStorePaymentUnderThePaymentIdKey() {
        //when
        paymentRepository.save(PAYMENT);

        //then
        assertThat(paymentRepository.getById(PAYMENT_ID)).isEqualTo(PAYMENT);
    }
}
