package io.lenur.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ApplicationRunner.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MailProperties mailProperties;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Application property color: {}", applicationProperties.getColor());
        LOGGER.info("Application property lang: {}", applicationProperties.getLang());
        LOGGER.info("Application property theme: {}", applicationProperties.getTheme());

        LOGGER.info("Mail property protocol: {}", mailProperties.getProtocol());
        LOGGER.info("Mail property host: {}", mailProperties.getHost());
        LOGGER.info("Mail property port: {}", mailProperties.getPort());
        LOGGER.info("Mail property username: {}", mailProperties.getUsername());
        LOGGER.info("Mail property password: {}", mailProperties.getPassword());
        LOGGER.info("Mail property recipients: {}", String.join(" ", mailProperties.getRecipients()));
    }
}