package com.dominikcebula.words.generator.application.words.generator;

import com.dominikcebula.words.generator.application.domain.*;
import com.dominikcebula.words.generator.application.words.generator.port.OutputDataDisplay;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WordsGenerator {
    private final WordsMap wordsMap;
    private final OutputDataDisplay outputDataDisplay;

    private final WordKeyFactory wordKeyFactory = new WordKeyFactory();

    public void generatePossibleWords(String characters) {
        WordKey wordKey = wordKeyFactory.createFromWord(characters);

        WordsSet wordsSet = wordsMap.get(wordKey);

        wordsSet.getWordValues()
                .stream()
                .map(WordValue::value)
                .forEach(outputDataDisplay::displayLine);
    }
}
