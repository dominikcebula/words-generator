package com.dominikcebula.words.generator.application.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordsMap {
    private final Map<WordKey, WordsSet> wordsData = new LinkedHashMap<>();

    public void add(WordKey wordKey, WordValue wordValue) {
        wordsData.computeIfAbsent(wordKey, key -> new WordsSet())
                .add(wordValue);
    }

    public String textRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();

        for (WordKey wordKey : wordsData.keySet()) {
            stringBuilder.append(wordKey.textRepresentation());
            stringBuilder.append("\n");

            for (WordValue wordValue : wordsData.get(wordKey).getWordValues()) {
                stringBuilder.append(wordValue.textRepresentation());
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
