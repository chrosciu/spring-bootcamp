package com.chrosciu.shop.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

class HashMapPaymentRepositoryTest {

    private static final Payment PAYMENT = Payment.builder()
            .id("1")
            .money(PolishMoney.of(100))
            .timestamp(Instant.now())
            .status(PaymentStatus.STARTED)
            .build();

    private HashMapPaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new HashMapPaymentRepository();
    }

    @DisplayName("Should save payment")
    @Test
    void shouldSavePay() {
        paymentRepository.save(PAYMENT);
        Assertions.assertEquals(PAYMENT, paymentRepository.findById(PAYMENT.getId()).get());
    }

    @DisplayName("Should return saved payment")
    @Test
    void shouldReturnSavedPayment() {
        paymentRepository.save(PAYMENT);
        Optional<Payment> payment = paymentRepository.findById(PAYMENT.getId());
        Assertions.assertNotNull(payment.get());
    }
}
