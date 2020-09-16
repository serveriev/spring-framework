package io.lenur.conditional.config;

import io.lenur.conditional.service.impl.GreetingMessageImpl;
import io.lenur.conditional.service.impl.HelloMessageImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    @ConditionalOnProperty(name = "helloMessageBean.enabled", havingValue="true")
    public HelloMessageImpl helloMessageBean() {
        return new HelloMessageImpl();
    }

    @Bean
    @ConditionalOnMissingBean(HelloMessageImpl.class)
    public GreetingMessageImpl greetingMessageBean() {
        return new GreetingMessageImpl();
    }
}
