package com.dominikcebula.words.generator.application.words.map.value;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordsSet {
    private final Set<WordValue> words = new LinkedHashSet<>();

    public void add(WordValue wordValue) {
        words.add(wordValue);
    }

    public Set<WordValue> getWordValues() {
        return words;
    }

    public String toLineValue() {
        StringBuilder stringBuilder = new StringBuilder();

        for (WordValue wordValue : words) {
            stringBuilder.append(wordValue.toLineValue());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
