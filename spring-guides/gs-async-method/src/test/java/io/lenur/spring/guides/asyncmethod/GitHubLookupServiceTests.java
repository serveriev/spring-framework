package io.lenur.spring.guides.asyncmethod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class GitHubLookupServiceTests {
    @Autowired
    private GitHubLookupService gitHubLookupService;

    @Test
    public void findUser() throws InterruptedException, ExecutionException {
        CompletableFuture<User> page = gitHubLookupService.findUser("serveriev");
        page.join();

        User user = page.get();
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("lenur");
    }
}
