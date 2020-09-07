package io.lenur.spring.guides.messagingjms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Sender.class);

    @JmsListener(destination = "mailbox", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(Email email) {
        LOGGER.info("Received <{}>", email);
    }
}