package com.dominikcebula.words.generator.io;

import com.dominikcebula.words.generator.application.dataset.generator.port.InputWordsReader;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InMemoryInputWordsReader implements InputWordsReader {
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
