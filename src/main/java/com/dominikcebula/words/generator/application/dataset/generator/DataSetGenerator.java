package com.dominikcebula.words.generator.application.dataset.generator;

import com.dominikcebula.words.generator.application.dataset.generator.port.InputWordsReader;
import com.dominikcebula.words.generator.application.dataset.generator.port.OutputDatasetWriter;
import com.dominikcebula.words.generator.application.words.map.WordsMap;
import com.dominikcebula.words.generator.application.words.map.key.WordKey;
import com.dominikcebula.words.generator.application.words.map.key.WordKeyFactory;
import com.dominikcebula.words.generator.application.words.map.value.WordValue;
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

        outputDatasetWriter.write(wordsMap.toLinesValues());
    }
}
