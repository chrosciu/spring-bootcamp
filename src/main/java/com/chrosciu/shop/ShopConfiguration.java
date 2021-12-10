package com.chrosciu.shop;

import com.chrosciu.shop.orders.OrderService;
import com.chrosciu.shop.payments.PaymentService;
import com.chrosciu.shop.products.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ShopConfiguration {
    @Bean
    public ShopService shopService(OrderService orderService, PaymentService paymentService, ProductService productService) {
        return new ShopService(orderService, paymentService, productService);
    }

    @Bean
    @Profile("!test")
    public ShopRunner shopRunner(ShopService shopService) {
        return new ShopRunner(shopService);
    }
}
