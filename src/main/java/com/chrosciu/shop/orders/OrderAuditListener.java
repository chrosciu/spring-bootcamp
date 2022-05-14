package com.chrosciu.shop.orders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class OrderAuditListener {
    private final String logPrefix;

    @EventListener
    public void onPaymentStatusChange(OrderAuditEvent event) {
        log.info("{}: {} {}", logPrefix, event.getType(), event.getOrder());
    }
}
