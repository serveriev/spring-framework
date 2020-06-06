package io.lenur.spring.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "io.lenur.spring.blog.service",
    "io.lenur.spring.blog.dao"
})
public class ApplicationConfig {
}
