package com.dominikcebula.words.generator.application.dataset.generator;

import com.dominikcebula.words.generator.application.dataset.generator.port.InputWordsReader;
import com.dominikcebula.words.generator.application.dataset.generator.port.OutputDatasetWriter;
import com.dominikcebula.words.generator.application.domain.WordKey;
import com.dominikcebula.words.generator.application.domain.WordKeyFactory;
import com.dominikcebula.words.generator.application.domain.WordValue;
import com.dominikcebula.words.generator.application.domain.WordsMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataSetGenerator {
    private final WordKeyFactory wordKeyFactory = new WordKeyFactory();

    private final InputWordsReader inputWordsReader;
    private final OutputDatasetWriter outputDatasetWriter;

    public void generate() {
        WordsMap wordsMap = new WordsMap();

        while (inputWordsReader.hasNextLine()) {
            String word = inputWordsReader.getNextLine();

            WordKey wordKey = wordKeyFactory.createFromWord(word);

            wordsMap.add(wordKey, new WordValue(word));
        }

        outputDatasetWriter.write(wordsMap.textRepresentation());
    }
}
