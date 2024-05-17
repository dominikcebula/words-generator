package com.dominikcebula.words.generator.application.dataset.generator;

import com.dominikcebula.words.generator.application.dataset.generator.port.InputWordsReader;
import com.dominikcebula.words.generator.application.dataset.generator.port.OutputDatasetWriter;
import com.dominikcebula.words.generator.application.domain.WordsMap;
import com.dominikcebula.words.generator.application.domain.WordsMapKey;
import com.dominikcebula.words.generator.application.domain.WordsMapKeyFactory;
import com.dominikcebula.words.generator.application.domain.WordsMapValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PreprocessedDataSetGenerator {
    private final WordsMapKeyFactory wordsMapKeyFactory = new WordsMapKeyFactory();

    private final InputWordsReader inputWordsReader;
    private final OutputDatasetWriter outputDatasetWriter;

    public void generate() {
        WordsMap wordsMap = new WordsMap();

        while (inputWordsReader.hasNextWord()) {
            String word = inputWordsReader.getNextWord();

            WordsMapKey wordsMapKey = wordsMapKeyFactory.createFromWord(word);

            wordsMap.add(wordsMapKey, new WordsMapValue(word));
        }

        outputDatasetWriter.write(wordsMap.textRepresentation());
    }
}
