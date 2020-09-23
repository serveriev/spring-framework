package io.lenur.resource.loader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClasspathStringLoaderTest {
    @Autowired
    @Qualifier("classpath")
    private StringLoader classpathLoader;

    @Test
    void loadStringLoader() {
        assertNotNull(classpathLoader);
    }

    @Test
    void loadStringFromFile() {
        String fileText = classpathLoader.getString();
        String expectedText = "one two third one two one";

        assertEquals(expectedText, fileText);
    }
}
