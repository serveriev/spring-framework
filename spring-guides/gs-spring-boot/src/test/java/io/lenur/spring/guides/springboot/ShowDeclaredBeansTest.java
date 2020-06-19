package io.lenur.spring.guides.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShowDeclaredBeansTest {
    @Autowired
    private ShowDeclaredBeans declaredBeans;

    @Test
    void showAllBeans() throws Exception {
        declaredBeans.run();
    }
}
