package io.lenur.resource;

import java.util.Map;

import io.lenur.resource.loader.StringLoader;
import io.lenur.resource.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ApplicationRunner.class);

    @Autowired
    @Qualifier("classpath")
    private StringLoader classpathLoader;

    @Autowired
    @Qualifier("resource")
    private StringLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        Map<String, Integer> words = StringUtil.getCountWords(classpathLoader.getString());

        LOGGER.info("----- Using by classpath loader -----");
        for (String key : words.keySet()) {
            LOGGER.info("Word: {}. Counts: {}", key, words.get(key));
        }

        words = StringUtil.getCountWords(resourceLoader.getString());
        LOGGER.info("----- Using by resource loader -----");
        for (String key : words.keySet()) {
            LOGGER.info("Word: {}. Counts: {}", key, words.get(key));
        }
    }
}