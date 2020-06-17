package io.lenur.spring.guides.uploading.files;

import io.lenur.spring.guides.uploading.files.property.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class UploadingFilesApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadingFilesApplication.class, args);
    }
}
