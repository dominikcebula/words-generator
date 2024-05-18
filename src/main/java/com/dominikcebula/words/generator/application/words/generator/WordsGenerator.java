package com.dominikcebula.words.generator.application.words.generator;

import com.dominikcebula.words.generator.application.dataset.loader.PreprocessedDataSetLoader;
import com.dominikcebula.words.generator.application.domain.*;
import com.dominikcebula.words.generator.application.words.generator.port.OutputDataDisplay;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WordsGenerator {
    private PreprocessedDataSetLoader dataSetLoader;
    private OutputDataDisplay outputDataDisplay;

    private final WordKeyFactory wordKeyFactory = new WordKeyFactory();

    public void generatePossibleWords(String characters) {
        WordsMap wordsMap = dataSetLoader.load();
        WordKey wordKey = wordKeyFactory.createFromWord(characters);

        WordsSet wordsSet = wordsMap.get(wordKey);

        wordsSet.getWordValues()
                .stream()
                .map(WordValue::value)
                .forEach(outputDataDisplay::displayLine);
    }
}
