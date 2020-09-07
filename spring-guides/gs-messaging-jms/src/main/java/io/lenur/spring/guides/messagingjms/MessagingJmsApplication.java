package io.lenur.spring.guides.messagingjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class MessagingJmsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(MessagingJmsApplication.class, args);

        Sender sender = context.getBean(Sender.class);
        Email email = new Email("info@example.com", "Hello world");
        sender.send(email);
    }
}
