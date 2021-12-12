package com.chrosciu.shop.payments;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapPaymentRepository implements PaymentRepository {
    private final Map<String, Payment> storage = new HashMap<>();

    @Override
    public Payment save(Payment payment) {
        storage.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Optional<Payment> findById(String id) {
        Payment payment = storage.get(id);
        if (payment != null) {
            return Optional.of(payment);
        }
        return Optional.empty();
    }
}
