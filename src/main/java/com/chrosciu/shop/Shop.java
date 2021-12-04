package com.chrosciu.shop;

import com.chrosciu.shop.payments.PaymentRequest;
import com.chrosciu.shop.payments.PaymentService;
import com.chrosciu.shop.payments.PolishMoney;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Shop {
    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONFIG_LOCATION)) {
            var paymentService = applicationContext.getBean(PaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(PolishMoney.of(100))
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info("{}", payment);
        }
    }
}
