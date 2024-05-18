package com.dominikcebula.words.generator.application.dataset.loader;

import com.dominikcebula.words.generator.application.dataset.loader.port.InputDataSetReader;
import com.dominikcebula.words.generator.application.domain.WordKey;
import com.dominikcebula.words.generator.application.domain.WordValue;
import com.dominikcebula.words.generator.application.domain.WordsMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PreprocessedDataSetLoader {
    private final InputDataSetReader inputDataSetReader;

    public WordsMap load() {
        WordsMap wordsMap = new WordsMap();
        WordKey currentKey = null;

        while (inputDataSetReader.hasNextLine()) {
            String dataLine = inputDataSetReader.getNextLine();

            if (WordKey.isWordKey(dataLine)) {
                currentKey = WordKey.fromLineValue(dataLine);
            } else if (WordValue.isWordValue(dataLine)) {
                WordValue wordValue = WordValue.fromLineValue(dataLine);

                if (currentKey == null)
                    throw new IllegalStateException("Tried adding word value without having key yet");
                wordsMap.add(currentKey, wordValue);
            }
        }

        return wordsMap;
    }
}
