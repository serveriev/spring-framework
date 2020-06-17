package io.lenur.spring.guides.rest.service.controller;

import io.lenur.spring.guides.rest.service.domain.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/greeting")
public class GreetingController {
    private static final String CONTENT_TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public Greeting getGreeting(
            @RequestParam(name = "name", defaultValue = "World") String name) {
        String content = String.format(CONTENT_TEMPLATE, name);

        return new Greeting(counter.incrementAndGet(), content);
    }
}
