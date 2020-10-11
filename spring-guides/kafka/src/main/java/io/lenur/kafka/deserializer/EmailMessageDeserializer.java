package io.lenur.kafka.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lenur.kafka.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class EmailMessageDeserializer implements Deserializer<EmailMessage> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public EmailMessage deserialize(String topic, byte[] data) {
        try {
            String content = new String(data, StandardCharsets.UTF_8);
            return objectMapper.readValue(content, EmailMessage.class);
        } catch (Exception e) {
            log.error("Unable to deserialize the message {}", data, e);
            return null;
        }
    }

    @Override
    public void close() {
    }
}
