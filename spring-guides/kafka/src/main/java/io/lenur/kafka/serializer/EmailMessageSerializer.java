package io.lenur.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lenur.kafka.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class EmailMessageSerializer implements Serializer<EmailMessage> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, EmailMessage emailMessage) {
        try {
            return objectMapper.writeValueAsBytes(emailMessage);
        } catch (JsonProcessingException exception) {
            log.error("Unable to serialize the object {}", emailMessage, exception);
            return null;
        }
    }

    @Override
    public void close() {
    }
}
