package com.chrosciu.shop;

import com.chrosciu.shop.payments.FakePaymentService;
import com.chrosciu.shop.payments.IncrementalPaymentIdGenerator;
import com.chrosciu.shop.payments.LoggingPaymentService;
import com.chrosciu.shop.payments.PaymentIdGenerator;
import com.chrosciu.shop.payments.PaymentRequest;
import com.chrosciu.shop.payments.PaymentService;
import com.chrosciu.shop.payments.PolishMoney;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Shop {
    public static void main(String[] args) {
        PaymentIdGenerator paymentIdGenerator = new IncrementalPaymentIdGenerator();
        PaymentService fakePaymentService = new FakePaymentService(paymentIdGenerator);
        PaymentService paymentService = new LoggingPaymentService(fakePaymentService);
        var paymentRequest = PaymentRequest.builder()
                .money(PolishMoney.of(100))
                .build();
        var payment = paymentService.process(paymentRequest);
        log.info("{}", payment);
    }
}
