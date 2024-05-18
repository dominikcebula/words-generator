package com.dominikcebula.words.generator.application.words.query;

import com.dominikcebula.words.generator.application.domain.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WordsQuery {
    private final WordsMap wordsMap;

    private final WordKeyFactory wordKeyFactory = new WordKeyFactory();

    public List<String> queryPossibleWords(String characters) {
        WordKey wordKey = wordKeyFactory.createFromWord(characters);

        WordsSet wordsSet = wordsMap.get(wordKey);

        return wordsSet.getWordValues()
                .stream()
                .map(WordValue::value)
                .toList();
    }
}
