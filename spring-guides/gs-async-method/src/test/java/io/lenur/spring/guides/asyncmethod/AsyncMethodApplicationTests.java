package io.lenur.spring.guides.asyncmethod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AsyncMethodApplicationTests {
    @Autowired
    private AppRunner appRunner;

    @Autowired
    private AsyncConfig asyncConfig;

    @Autowired
    private GitHubLookupService gitHubLookupService;

    @Test
    void contextLoads() {
        assertThat(appRunner).isNotNull();
        assertThat(gitHubLookupService).isNotNull();
        assertThat(asyncConfig).isNotNull();
    }
}
