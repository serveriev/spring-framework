package io.lenur.multimodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.lenur.multimodule")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
