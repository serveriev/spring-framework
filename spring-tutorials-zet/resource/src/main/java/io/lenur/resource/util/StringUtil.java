package io.lenur.resource.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
    public static Map<String, Integer> getCountWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();

        String[] words = text.split("\\s+");
        for (String word: words) {
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.containsKey(word) ? wordCount.get(word) + 1 : 1);
            }
        }

        return wordCount;
    }
}
