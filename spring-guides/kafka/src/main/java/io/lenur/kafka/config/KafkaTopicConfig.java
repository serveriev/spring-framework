package io.lenur.kafka.config;

import io.lenur.kafka.constant.KafkaConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Arrays;

@Configuration
public class KafkaTopicConfig {
// we no need to create KafkaAdmin bean when we are using spring boot
//    @Bean
//    public KafkaAdmin admin() {
//        Map<String, Object> configs = new HashMap<>();
//
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//
//        return new KafkaAdmin(configs);
//    }

    @Bean
    public NewTopic email() {
        return TopicBuilder.name(KafkaConstant.TOPIC_EMAIL)
                .partitions(10)
                .replicas(1)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "snappy")
                .compact()
                .build();
    }

    @Bean
    public NewTopic sms() {
        return TopicBuilder.name(KafkaConstant.TOPIC_SMS)
                .partitions(10)
                .replicas(1)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }

    @Bean
    public NewTopic push() {
        return TopicBuilder.name(KafkaConstant.TOPIC_PUSH)
                .assignReplicas(0, Arrays.asList(0, 1))
                .assignReplicas(1, Arrays.asList(1, 2))
                .assignReplicas(2, Arrays.asList(2, 0))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "gzip")
                .build();
    }
}
