package com.dominikcebula.words.generator.io;

import com.dominikcebula.words.generator.application.dataset.generator.port.InputWordsReader;
import com.dominikcebula.words.generator.application.dataset.loader.port.InputDataSetReader;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

public class FileInputReader implements InputWordsReader, InputDataSetReader, AutoCloseable {
    private final BufferedReader bufferedReader;

    public FileInputReader(Path filePath) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(filePath.toFile()));
    }

    @SneakyThrows
    @Override
    public boolean hasNextLine() {
        return bufferedReader.ready();
    }

    @SneakyThrows
    @Override
    public String getNextLine() {
        return bufferedReader.readLine();
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
