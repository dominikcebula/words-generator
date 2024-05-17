package com.dominikcebula.words.generator.io;

import com.dominikcebula.words.generator.application.dataset.generator.port.InputWordsReader;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

public class FileInputWordsReader implements InputWordsReader, AutoCloseable {
    private final BufferedReader bufferedReader;

    public FileInputWordsReader(Path wordsFilePath) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(wordsFilePath.toFile()));
    }

    @SneakyThrows
    @Override
    public boolean hasNextWord() {
        return bufferedReader.ready();
    }

    @SneakyThrows
    @Override
    public String getNextWord() {
        return bufferedReader.readLine();
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
