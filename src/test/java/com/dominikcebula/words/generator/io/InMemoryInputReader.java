package com.dominikcebula.words.generator.io;

import com.dominikcebula.words.generator.application.dataset.generator.port.InputWordsReader;
import com.dominikcebula.words.generator.application.dataset.loader.port.InputDataSetReader;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InMemoryInputReader implements InputWordsReader, InputDataSetReader {
    private Iterator<String> dataIterator = Collections.emptyIterator();

    public void setData(List<String> data) {
        dataIterator = data.iterator();
    }

    @Override
    public boolean hasNextLine() {
        return dataIterator.hasNext();
    }

    @Override
    public String getNextLine() {
        return dataIterator.next();
    }
}
