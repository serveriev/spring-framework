package io.lenur.spring.guides.messagingredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MessagingRedisApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingRedisApplication.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(MessagingRedisApplication.class, args);

        StringRedisTemplate template = context.getBean(StringRedisTemplate.class);
        Receiver receiver = context.getBean(Receiver.class);

        while (receiver.getCount() == 0) {
            LOGGER.info("Sending message...");
            template.convertAndSend("chat", "Hello from Redis!");
            TimeUnit.MILLISECONDS.sleep(500L);
        }
    }
}
