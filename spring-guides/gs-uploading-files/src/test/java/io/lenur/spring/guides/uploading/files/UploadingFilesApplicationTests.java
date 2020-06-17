package io.lenur.spring.guides.uploading.files;

import io.lenur.spring.guides.uploading.files.controller.FileUploadController;
import io.lenur.spring.guides.uploading.files.runner.StorageRunner;
import io.lenur.spring.guides.uploading.files.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UploadingFilesApplicationTests {
    @Autowired
    private FileUploadController fileUploadController;

    @Autowired
    private StorageRunner storageRunner;

    @Autowired
    private StorageService storageService;

    @Test
    void contextLoads() {
        assertThat(fileUploadController).isNotNull();
        assertThat(storageRunner).isNotNull();
        assertThat(storageService).isNotNull();
    }
}
