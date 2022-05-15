package com.chrosciu.shop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Slf4j
public class ContextListener {
    @EventListener
    public void onContextRefreshed(ContextRefreshedEvent refreshedEvent) {
        log.info("Spring context refreshed: {}", refreshedEvent);
    }

    @EventListener
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info("Application event: {}", applicationEvent);
    }
}
