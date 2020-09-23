package io.lenur.resource.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {
    @Test
    void passAnEmptyString() {
        Map<String, Integer> wordsCount = StringUtil.getCountWords("");
        assertTrue(wordsCount.isEmpty());
    }

    @Test
    void passNotAnEmptyString() {
        Map<String, Integer> wordsCount = StringUtil.getCountWords("one one two");
        assertEquals(2, wordsCount.get("one"));
        assertEquals(1, wordsCount.get("two"));
    }
}
