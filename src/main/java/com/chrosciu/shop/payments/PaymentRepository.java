package com.chrosciu.shop.payments;

import java.util.Optional;

public interface PaymentRepository {
    /* Save payment and return this one */
    Payment save(Payment payment);
    /* Find payment by given id */
    Optional<Payment> findById(String id);
}
