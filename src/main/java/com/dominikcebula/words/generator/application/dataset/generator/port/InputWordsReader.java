package com.dominikcebula.words.generator.application.dataset.generator.port;

public interface InputWordsReader {
    boolean hasNextWord();

    String getNextWord();
}
