package io.lenur.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private String color;
    private String lang;
    private String theme;
}
