package io.lenur.spring.guides.messagingredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MessagingRedisApplicationTests {

    @Autowired
    private Receiver receiver;

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    private MessageListenerAdapter messageListenerAdapter;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        assertThat(receiver).isNotNull();
        assertThat(redisMessageListenerContainer).isNotNull();
        assertThat(messageListenerAdapter).isNotNull();
        assertThat(stringRedisTemplate).isNotNull();
    }
}
