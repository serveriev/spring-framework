package io.lenur.mail.sender;

import io.lenur.mail.dto.Message;

public interface Notification {
    boolean send(Message message);
}
