package io.lenur.spring.guides.consuming.rest.task;

import io.lenur.spring.guides.consuming.rest.constant.Api;
import io.lenur.spring.guides.consuming.rest.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class QuoterFetcherTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoterFetcherTask.class);

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 2000)
    public void fetchQuoter() {
        Quote quote = restTemplate.getForObject(Api.URL, Quote.class);
        Objects.requireNonNull(quote);
        LOGGER.info(quote.toString());
    }
}