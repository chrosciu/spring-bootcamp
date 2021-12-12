package com.chrosciu.shop.payments;

public interface PaymentService {
    Payment process(PaymentRequest paymentRequest);
}
