package io.lenur.spring.guides.uploading.files.runner;

import io.lenur.spring.guides.uploading.files.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StorageRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageRunner.class);

    @Autowired
    private StorageService storageService;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Deleting the upload folder");
        storageService.deleteAll();

        LOGGER.info("Init the upload folder");
        storageService.init();
    }
}
