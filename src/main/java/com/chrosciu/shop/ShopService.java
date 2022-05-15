package com.chrosciu.shop;

import com.chrosciu.shop.orders.Order;
import com.chrosciu.shop.orders.OrderService;
import com.chrosciu.shop.payments.Payment;
import com.chrosciu.shop.payments.PaymentRequest;
import com.chrosciu.shop.payments.PaymentService;
import com.chrosciu.shop.products.Product;
import com.chrosciu.shop.products.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShopService {
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final ProductService productService;

    public Product addProduct(Product product) {
        return productService.add(product);
    }

    public List<Product> getProducts() {
        return productService.getAll();
    }

    public Order placeOrder(Order order) {
        return orderService.add(order);
    }

    public Payment payForOrder(long orderId) {
        var order = orderService.getBy(orderId);
        var paymentRequest = PaymentRequest.builder()
                .money(order.getTotalPrice())
                .build();
        var payment = paymentService.process(paymentRequest);
        order.setPayment(payment);
        orderService.update(order);
        return payment;
    }
}

