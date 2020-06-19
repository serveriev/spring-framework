package io.lenur.spring.guides.messagingstompwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.concurrent.TimeUnit;

@Controller
public class GreetingController {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(GreetingController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1L);

        String msg = HtmlUtils.htmlEscape(message.getText());
        String content = String.format("Hello, %s!", msg);
        LOGGER.info("Incoming message: \"{}\"", msg);

        return new Greeting(content);
    }
}