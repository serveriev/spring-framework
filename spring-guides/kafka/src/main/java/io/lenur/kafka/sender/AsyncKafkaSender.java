package io.lenur.kafka.sender;

import io.lenur.kafka.constant.KafkaConstant;
import io.lenur.kafka.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class AsyncKafkaSender implements KafkaSender {
    private final KafkaTemplate<String, EmailMessage> template;

    public AsyncKafkaSender(KafkaTemplate<String, EmailMessage> template) {
        this.template = template;
    }

    @Override
    public void send(EmailMessage message) {
        ListenableFuture<SendResult<String, EmailMessage>> future = template.send(KafkaConstant.TOPIC_EMAIL, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, EmailMessage> result) {
                log.info("Async kafka message {} was successfully sent", result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable exception) {
                log.error("Async kafka message was failed. The message {}. Stack trace {}",
                        exception.getMessage(),
                        // not a good idea to fill stack trace.
                        exception.fillInStackTrace());
            }
        });
    }
}
