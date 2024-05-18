package com.dominikcebula.words.generator.application.words.map;

import com.dominikcebula.words.generator.application.words.map.key.WordKey;
import com.dominikcebula.words.generator.application.words.map.value.WordValue;
import com.dominikcebula.words.generator.application.words.map.value.WordsSet;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordsMap {
    private final Map<WordKey, WordsSet> wordsData = new LinkedHashMap<>();

    public void add(WordKey wordKey, WordValue wordValue) {
        wordsData.computeIfAbsent(wordKey, key -> new WordsSet())
                .add(wordValue);
    }

    public WordsSet get(WordKey wordKey) {
        return wordsData.getOrDefault(wordKey, new WordsSet());
    }

    public String toLinesValues() {
        StringBuilder stringBuilder = new StringBuilder();

        for (WordKey wordKey : wordsData.keySet()) {
            stringBuilder.append(wordKey.toLineValue());
            stringBuilder.append("\n");

            WordsSet wordsSet = wordsData.get(wordKey);
            stringBuilder.append(wordsSet.toLineValue());
        }

        return stringBuilder.toString();
    }
}
