package com.chrosciu.shop.orders;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class OrderAuditEvent extends ApplicationEvent {
    @Getter
    private final transient OrderAuditEventType type;
    @Getter
    private final transient Order order;

    public OrderAuditEvent(Object source, OrderAuditEventType type, Order order) {
        super(source);
        this.type = type;
        this.order = order;
    }
}
