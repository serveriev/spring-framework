package io.lenur.kafka;

import io.lenur.kafka.constant.KafkaConstant;
import io.lenur.kafka.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @KafkaListener(topics = KafkaConstant.TOPIC_EMAIL, groupId = "${spring.kafka.consumer.group-id}")
    public void listen(EmailMessage message) {
        log.info("Received message '{}' from Kafka.", message);
    }
}
