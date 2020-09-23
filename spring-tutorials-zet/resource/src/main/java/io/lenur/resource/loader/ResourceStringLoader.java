package io.lenur.resource.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@Qualifier("resource")
public class ResourceStringLoader implements StringLoader {
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String getString() {
        try {
            Resource resource = resourceLoader.getResource("classpath:thermopylae.txt");
            Path path = Paths.get(resource.getURI());
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            return String.join(" ", lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
