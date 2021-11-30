package com.chrosciu.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FakePaymentServiceTest {
    private static final String PAYMENT_ID = "1";
    private static final FastMoney MONEY = PolishMoney.of(1000);
    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();

    @Mock
    private PaymentIdGenerator paymentIdGenerator;

    @InjectMocks
    private FakePaymentService paymentService;

    @BeforeEach
    void setUp() {
        when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
    }

    @DisplayName("Should assign generated id to created payment")
    @Test
    void shouldAssignGeneratedIdToCreatedPayment() {
        var payment = paymentService.process(PAYMENT_REQUEST);
        assertEquals(PAYMENT_ID, payment.getId());
    }

    @DisplayName("Should assign money from payment request to created payment")
    @Test
    void shouldAssignMoneyFromPaymentRequestToCreatedPayment() {
        var payment = paymentService.process(PAYMENT_REQUEST);
        assertEquals(MONEY, payment.getMoney());
    }

    @DisplayName("Should assign timestamp to created payment")
    @Test
    void shouldAssignTimestampToCreatedPayment() {
        var payment = paymentService.process(PAYMENT_REQUEST);
        assertNotNull(payment.getTimestamp());
    }
}
