package io.lenur.profile;

import io.lenur.profile.bean.Notificator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class ApplicationRunner implements CommandLineRunner {
    private static final Logger LOGGER = LogManager
            .getLogger(ApplicationRunner.class);

    private final Environment environment;
    private final Notificator notificator;

    public ApplicationRunner(Environment environment, Notificator notificator) {
        this.environment = environment;
        this.notificator = notificator;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Active profiles: {}", Arrays.toString(environment.getActiveProfiles()));

        LOGGER.info("Get notificator message: {}", notificator.getNotificationMessage());
    }
}