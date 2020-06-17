package io.lenur.spring.guides.scheduling.task;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ScheduledTaskTest {
    @SpyBean
    private ScheduledTask scheduledTask;

    @Test
    public void reportCurrentTime() {
        await().atMost(Duration.TWO_SECONDS).untilAsserted(() -> {
            verify(scheduledTask, atLeast(2)).reportCurrentTime();
        });
    }
}
