package com.chrosciu.shop.payments;

import org.javamoney.moneta.FastMoney;

import javax.money.Monetary;

public class PolishMoney {
    public static FastMoney of(Number number) {
        var currencyUnit = Monetary.getCurrency("PLN");
        return FastMoney.of(number, currencyUnit);
    }
}
