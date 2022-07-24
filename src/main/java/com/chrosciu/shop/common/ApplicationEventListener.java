package com.chrosciu.shop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationEventListener {
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("Application event caught: {}", event);
    }
}
