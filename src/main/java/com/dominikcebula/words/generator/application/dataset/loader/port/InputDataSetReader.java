package com.dominikcebula.words.generator.application.dataset.loader.port;

public interface InputDataSetReader {
    boolean hasNextLine();

    String getNextLine();
}
