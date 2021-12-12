package com.chrosciu.shop.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IncrementalPaymentIdGeneratorTest {
    private static final String ID_FORMAT = "\\d{10}";

    private final IncrementalPaymentIdGenerator paymentIdGenerator = new IncrementalPaymentIdGenerator(0);

    @DisplayName("Should generate valid id")
    @Test
    void shouldGenerateValidId() {
        String id = paymentIdGenerator.getNext();
        Assertions.assertTrue(id.matches(ID_FORMAT));
    }

    @DisplayName("Should generate id by increasing the value of previous one")
    @Test
    void shouldGenerateIdByIncreasingTheValueOfPreviousOne() {
        long firstIdValue = Long.parseLong(paymentIdGenerator.getNext());
        long secondIdValue = Long.parseLong(paymentIdGenerator.getNext());
        Assertions.assertEquals(firstIdValue + 1, secondIdValue);
    }
}
