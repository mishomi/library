package com.solvd.library.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class TextWordCounter {

    private TextWordCounter() {
    }

    public static void countUniqueWords() {
        try {
            URL resource = TextWordCounter.class.getClassLoader().getResource("logs/book.txt");
            if (resource == null) {
                throw new IllegalStateException("book.txt not found in resources");
            }

            String text = FileUtils.readFileToString(new File(resource.toURI()), StandardCharsets.UTF_8);

            String cleaned = StringUtils.lowerCase(text).replaceAll("[^a-z0-9']", " ");

            Set<String> uniqueWords = Arrays.stream(StringUtils.split(cleaned))
                    .collect(Collectors.toSet());

            FileUtils.writeStringToFile(
                    new File("target/unique-words.txt"),
                    "Unique words: " + uniqueWords.size() + System.lineSeparator() + uniqueWords,
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}