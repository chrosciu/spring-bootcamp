package com.chrosciu.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import static com.chrosciu.shop.orders.OrderAuditEventType.CREATE;
import static com.chrosciu.shop.orders.OrderAuditEventType.UPDATE;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Order add(Order order) {
        var savedOrder = orderRepository.save(order);
        eventPublisher.publishEvent(new OrderAuditEvent(this, CREATE, savedOrder));
        return savedOrder;
    }

    public Order getBy(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order) {
        eventPublisher.publishEvent(new OrderAuditEvent(this, UPDATE, order));
        orderRepository.update(order);
    }
}
