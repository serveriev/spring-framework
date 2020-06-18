package io.lenur.spring.guides.messagingjms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.concurrent.TimeUnit;

@EnableJms
@SpringBootApplication
public class MessagingJmsApplication {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(MessagingJmsApplication.class);

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context =
                SpringApplication.run(MessagingJmsApplication.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        LOGGER.info("Sending an email message.");
        int countSend = 5;
        while (countSend-- > 0) {
            Email pojo = new Email("info@example.com " + countSend, "Hello " + countSend);
            jmsTemplate.convertAndSend("mailbox", pojo);
            TimeUnit.SECONDS.sleep(1);
        }

        System.exit(0);
    }
}
