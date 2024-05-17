package com.dominikcebula.words.generator.application.domain;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class WordsMap {
    private final Map<WordsMapKey, Set<WordsMapValue>> wordsData = new LinkedHashMap<>();

    public void add(WordsMapKey wordsMapKey, WordsMapValue wordsMapValue) {
        wordsData.computeIfAbsent(wordsMapKey, key -> new LinkedHashSet<>())
                .add(wordsMapValue);
    }

    public String textRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();

        for (WordsMapKey wordsMapKey : wordsData.keySet()) {
            stringBuilder.append(wordsMapKey.textRepresentation());
            stringBuilder.append("\n");

            for (WordsMapValue wordsMapValue : wordsData.get(wordsMapKey)) {
                stringBuilder.append(wordsMapValue.textRepresentation());
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
