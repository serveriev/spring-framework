package io.lenur.kafka.sender;

import io.lenur.kafka.constant.KafkaConstant;
import io.lenur.kafka.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class SyncKafkaSender implements KafkaSender {
    private final KafkaTemplate<String, EmailMessage> template;

    public SyncKafkaSender(KafkaTemplate<String, EmailMessage> template) {
        this.template = template;
    }

    @Override
    public void send(EmailMessage message) {
        try {
            template
                    .send(KafkaConstant.TOPIC_EMAIL, message)
                    .get(10, TimeUnit.SECONDS);
            log.info("Async kafka message {} was successfully sent", message);
        } catch (ExecutionException exception) {
            String errorMessage = "Async kafka message was failed. The message {}";
            log.error(errorMessage, message);
        } catch (TimeoutException | InterruptedException exception) {
            String errorMessage = "Async kafka message was failed. Time is out or interrupted. The message {}. ";
            log.error(errorMessage, message);
        }
    }
}
