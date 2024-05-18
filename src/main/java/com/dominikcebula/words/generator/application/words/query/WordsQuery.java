package com.dominikcebula.words.generator.application.words.query;

import com.dominikcebula.words.generator.application.domain.*;
import com.dominikcebula.words.generator.application.words.query.port.OutputDataDisplay;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WordsQuery {
    private final WordsMap wordsMap;
    private final OutputDataDisplay outputDataDisplay;

    private final WordKeyFactory wordKeyFactory = new WordKeyFactory();

    public void queryPossibleWords(String characters) {
        WordKey wordKey = wordKeyFactory.createFromWord(characters);

        WordsSet wordsSet = wordsMap.get(wordKey);

        wordsSet.getWordValues()
                .stream()
                .map(WordValue::value)
                .forEach(outputDataDisplay::displayLine);
    }
}
