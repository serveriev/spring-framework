package io.lenur.datasourcebuilder.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix="datasource.h2")
    @ConditionalOnProperty(name="datasource", havingValue="h2")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.mysql")
    @ConditionalOnProperty(name="datasource", havingValue="mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
