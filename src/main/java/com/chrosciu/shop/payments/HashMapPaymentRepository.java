package com.chrosciu.shop.payments;

import java.util.Optional;

public class HashMapPaymentRepository implements PaymentRepository {
    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public Optional<Payment> findById(String id) {
        return Optional.empty();
    }
}
