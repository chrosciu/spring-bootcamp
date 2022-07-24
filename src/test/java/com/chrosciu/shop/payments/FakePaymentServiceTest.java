package com.chrosciu.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
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
    private FakePaymentService fakePaymentService;

    @BeforeEach
    void setUp() {
        when(paymentRepository.save(any(Payment.class))).then(returnsFirstArg());
    }

//    @BeforeEach
//    void setUp() {
////        paymentIdGenerator = new PaymentIdGenerator() {
////            @Override
////            public String getNext() {
////                return PAYMENT_ID;
////            }
////        };
////        paymentIdGenerator = mock(PaymentIdGenerator.class);
////        fakePaymentService = new FakePaymentService(paymentIdGenerator);
//    }

    @Test
    void shouldCopyMoneyFromPaymentRequestToPayment() {
        var payment = fakePaymentService.process(PAYMENT_REQUEST);
        assertThat(payment.getMoney()).isEqualTo(MONEY);
    }

    @Test
    void shouldReturnPaymentIdGeneratedByGenerator() {
        //given
        when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
        //when
        var payment = fakePaymentService.process(PAYMENT_REQUEST);
        //then
        assertThat(payment.getId()).isEqualTo(PAYMENT_ID);
    }



}
