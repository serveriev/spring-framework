package io.lenur.spring.di;

import io.lenur.spring.di.config.DependencyConfig;
import io.lenur.spring.di.service.ApplicationService;
import io.lenur.spring.di.service.ApplicationServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DependencyConfig.class);

        ApplicationService service = context.getBean(ApplicationServiceImpl.class);
        service.print();
    }
}
