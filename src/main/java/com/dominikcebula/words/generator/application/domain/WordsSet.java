package com.dominikcebula.words.generator.application.domain;

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
}
