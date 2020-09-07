package io.lenur.spring.guides.batchprocessing;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContextLoadTests {
    @Autowired
    private JobCompletionNotificationListener listener;

    @Autowired
    private FlatFileItemReader<Person> reader;

    @Autowired
    private PersonItemProcessor processor;

    @Test
    void contextLoads() {
        assertThat(listener).isNotNull();
        assertThat(reader).isNotNull();
        assertThat(processor).isNotNull();
    }
}
