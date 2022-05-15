package com.chrosciu.shop.orders;

import com.chrosciu.shop.payments.Payment;
import com.chrosciu.shop.payments.PolishMoney;
import com.chrosciu.shop.products.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Long id;
    @NonNull
    private List<Product> products;
    private Payment payment;

    public FastMoney getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(PolishMoney.zero(), FastMoney::add);
    }
}
