package io.lenur.spring.guides.consuming.rest.controller;

import io.lenur.spring.guides.consuming.rest.constant.Api;
import io.lenur.spring.guides.consuming.rest.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/quote")
public class QuoteController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Quote getQuote() {
        return restTemplate.getForObject(Api.URL, Quote.class);
    }
}
