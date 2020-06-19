package io.lenur.spring.guides.restservicecors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Greeting {
    private final long id;
    private final String content;

    public Greeting() {
        this.id = -1;
        this.content = "";
    }
}