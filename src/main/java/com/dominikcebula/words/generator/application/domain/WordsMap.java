package com.dominikcebula.words.generator.application.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordsMap {
    private final Map<WordsMapKey, Set<WordsMapValue>> wordsData = new HashMap<>();

    public void add(WordsMapKey wordsMapKey, WordsMapValue wordsMapValue) {
        wordsData.computeIfAbsent(wordsMapKey, key -> new HashSet<>())
                .add(wordsMapValue);
    }

    public String textRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();

        for (WordsMapKey wordsMapKey : wordsData.keySet()) {
            stringBuilder.append(wordsMapKey.textRepresentation());

            for (WordsMapValue wordsMapValue : wordsData.get(wordsMapKey)) {
                stringBuilder.append(wordsMapValue.textRepresentation());
            }
        }

        return stringBuilder.toString();
    }
}
