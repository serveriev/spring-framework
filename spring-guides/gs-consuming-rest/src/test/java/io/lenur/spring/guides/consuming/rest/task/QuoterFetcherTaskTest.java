package io.lenur.spring.guides.consuming.rest.task;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class QuoterFetcherTaskTest {
    @SpyBean
    private QuoterFetcherTask fetcherTask;

    @Test
    public void fetchQuoter() {
        await().atMost(Duration.TWO_SECONDS).untilAsserted(() -> {
            verify(fetcherTask, atLeast(1)).fetchQuoter();
        });
    }
}
