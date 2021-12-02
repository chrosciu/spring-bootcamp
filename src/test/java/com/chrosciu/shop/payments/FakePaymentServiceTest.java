package com.chrosciu.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private FakePaymentService paymentService;

    @BeforeEach
    void setUp() {
        when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
        when(paymentRepository.save(any(Payment.class))).then(returnsFirstArg());
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

    @DisplayName("Should save created payment to repository")
    @Test
    void shouldSaveCreatedPaymentToRepository() {
        var payment = paymentService.process(PAYMENT_REQUEST);
        verify(paymentRepository, times(1)).save(payment);
    }
}
