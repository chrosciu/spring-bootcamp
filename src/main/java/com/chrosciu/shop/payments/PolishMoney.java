package com.chrosciu.shop.payments;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.FastMoney;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PolishMoney {
    private static final String POLISH_CURRENCY_CODE = "PLN";
    private static final CurrencyUnit POLISH_CURRENCY_UNIT = Monetary.getCurrency(POLISH_CURRENCY_CODE);

    public static FastMoney of(Number number) {
        return FastMoney.of(number, POLISH_CURRENCY_UNIT);
    }

    public static FastMoney zero() {
        return FastMoney.zero(POLISH_CURRENCY_UNIT);
    }
}
