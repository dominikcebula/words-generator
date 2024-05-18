package com.dominikcebula.words.generator.application.query;

import com.dominikcebula.words.generator.application.words.map.WordsMap;
import com.dominikcebula.words.generator.application.words.map.key.WordKey;
import com.dominikcebula.words.generator.application.words.map.key.WordKeyFactory;
import com.dominikcebula.words.generator.application.words.map.value.WordValue;
import com.dominikcebula.words.generator.application.words.map.value.WordsSet;
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
