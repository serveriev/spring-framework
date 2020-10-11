package io.lenur.kafka.runner;

import io.lenur.kafka.dto.EmailMessage;
import io.lenur.kafka.sender.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {
    private final KafkaSender asyncSender;
    private final KafkaSender syncSender;

    public ApplicationRunner(
            @Qualifier("asyncKafkaSender") KafkaSender asyncSender,
            @Qualifier("syncKafkaSender") KafkaSender syncSender) {
        this.asyncSender = asyncSender;
        this.syncSender = syncSender;
    }

    @Override
    public void run(String... args) {
        EmailMessage emailMessage = new EmailMessage("Test message");

        log.info("The message {} is sending as asynchrony", emailMessage);
        //this.asyncSender.send(emailMessage);

        log.info("The message {} is sending as synchrony", emailMessage);
        this.syncSender.send(emailMessage);
    }
}