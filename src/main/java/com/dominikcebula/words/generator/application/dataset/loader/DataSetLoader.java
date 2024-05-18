package com.dominikcebula.words.generator.application.dataset.loader;

import com.dominikcebula.words.generator.application.dataset.loader.port.InputDataSetReader;
import com.dominikcebula.words.generator.application.words.map.WordsMap;
import com.dominikcebula.words.generator.application.words.map.key.WordKey;
import com.dominikcebula.words.generator.application.words.map.value.WordValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataSetLoader {
    private final InputDataSetReader inputDataSetReader;

    public WordsMap load() {
        WordsMap wordsMap = new WordsMap();
        WordKey currentKey = null;

        while (inputDataSetReader.hasNextLine()) {
            String dataLine = inputDataSetReader.getNextLine();

            if (WordKey.isWordKeyLine(dataLine)) {
                currentKey = WordKey.fromLineValue(dataLine);
            } else if (WordValue.isWordValueLine(dataLine)) {
                WordValue wordValue = WordValue.fromLineValue(dataLine);

                if (currentKey == null)
                    throw new IllegalStateException("Tried adding word value without having key yet");
                wordsMap.add(currentKey, wordValue);
            }
        }

        return wordsMap;
    }
}
