package io.lenur.resource.loader;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@Qualifier("classpath")
public class ClasspathStringLoader implements StringLoader {
    @Value("classpath:thermopylae.txt")
    private Resource resource;

    @Override
    public String getString() {
        try {
            Path path = Paths.get(resource.getURI());
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            return String.join(" ", lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
