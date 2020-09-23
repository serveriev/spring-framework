package io.lenur.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Configuration
@PropertySource("classpath:mail.properties")
@ConfigurationProperties
@Validated
@Getter
@Setter
public class MailProperties {
    @NotNull
    private String protocol;

    @NotNull
    private String host;

    @Max(65535)
    private int port;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private List<String> recipients;
}
