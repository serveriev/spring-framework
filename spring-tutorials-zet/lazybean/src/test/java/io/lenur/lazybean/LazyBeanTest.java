package io.lenur.lazybean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class LazyBeanTest {
    @Autowired
    private ApplicationContext beanFactory;

    @Test
    public void beanNotLoadedToContext() {
        boolean containLazyBean = beanFactory.containsBean(LazyBean.class.toString());
        assertFalse(containLazyBean);
    }
}
