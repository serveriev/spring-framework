package io.lenur.mail;

import io.lenur.mail.property.MailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MailProperties.class)
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
