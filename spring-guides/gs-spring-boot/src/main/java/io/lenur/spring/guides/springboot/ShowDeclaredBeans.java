package io.lenur.spring.guides.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ShowDeclaredBeans implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(ShowDeclaredBeans.class);

    private final ApplicationContext context;

    public ShowDeclaredBeans(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOGGER.info(beanName);
        }
    }
}

