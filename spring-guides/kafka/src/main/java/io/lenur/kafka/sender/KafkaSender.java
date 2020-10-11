package io.lenur.kafka.sender;

import io.lenur.kafka.dto.EmailMessage;

public interface KafkaSender {
    void send(EmailMessage message);
}
