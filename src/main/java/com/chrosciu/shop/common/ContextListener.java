package com.chrosciu.shop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContextListener {

    @EventListener
    public void onContextRefreshed(ContextRefreshedEvent refreshedEvent) {
        log.info("Spring context refreshed: {}", refreshedEvent);
    }

}
