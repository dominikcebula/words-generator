package com.dominikcebula.words.generator.io;

import com.dominikcebula.words.generator.application.dataset.generator.port.OutputDatasetWriter;

public class InMemoryOutputDatasetWriter implements OutputDatasetWriter {
    private String content;

    @Override
    public void write(String textRepresentation) {
        content = textRepresentation;
    }

    public String getContent() {
        return content;
    }
}
