package io.lenur.profile.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = "dev")
public class DevelopmentNotificator implements Notificator {
    @Value("${notify.message}")
    private String message;

    @Override
    public String getNotificationMessage() {
        return this.message;
    }
}
