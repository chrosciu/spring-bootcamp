package com.chrosciu.shop;

import com.chrosciu.shop.orders.Order;
import com.chrosciu.shop.payments.PolishMoney;
import com.chrosciu.shop.products.Product;
import com.chrosciu.shop.products.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ShopRunner implements CommandLineRunner {
    private final ShopService shopService;

    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring masterclass")
            .description("Praktyczny kurs Spring framework")
            .type(ProductType.VIDEO)
            .price(PolishMoney.of(1500))
            .build();

    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring guide")
            .description("Praktyczne ćwiczenia do samodzielnego wykonania")
            .type(ProductType.BOOK)
            .price(PolishMoney.of(200))
            .build();

    @Override
    public void run(String... args) throws Exception {
        shopService.addProduct(VIDEO_PRODUCT);
        shopService.addProduct(BOOK_PRODUCT);
        log.info(shopService.getProducts().toString());

        var order = new Order(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));
        shopService.placeOrder(order);
        var payment = shopService.payForOrder(order.getId());
        log.info(payment.getId());
    }
}
