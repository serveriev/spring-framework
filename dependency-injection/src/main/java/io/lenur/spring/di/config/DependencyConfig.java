package io.lenur.spring.di.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"io.lenur.spring.di.service"})
public class DependencyConfig {
}
