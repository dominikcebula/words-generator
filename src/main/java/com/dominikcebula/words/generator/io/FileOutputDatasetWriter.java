package com.dominikcebula.words.generator.io;

import com.dominikcebula.words.generator.application.dataset.generator.port.OutputDatasetWriter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class FileOutputDatasetWriter implements OutputDatasetWriter {
    private final Path dataSetOutputFilePath;

    @SneakyThrows
    @Override
    public void write(String lineValue) {
        Files.writeString(dataSetOutputFilePath, lineValue);
    }
}
