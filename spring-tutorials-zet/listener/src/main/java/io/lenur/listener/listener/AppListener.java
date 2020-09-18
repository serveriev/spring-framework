package io.lenur.listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppListener {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(AppListener.class);

    @EventListener(ApplicationReadyEvent.class)
    public void startApp() {
        LOGGER.info("The application startup time {}", LocalDateTime.now());
    }
}
