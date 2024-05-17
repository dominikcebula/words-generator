package com.dominikcebula.words.generator.cli.commands;

import com.dominikcebula.words.generator.application.dataset.generator.PreprocessedDataSetGenerator;
import com.dominikcebula.words.generator.cli.common.HelpOption;
import com.dominikcebula.words.generator.io.FileInputWordsReader;
import com.dominikcebula.words.generator.io.FileOutputDatasetWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;

import java.nio.file.Path;

@Command(name = "generate-dataset", description = "Creates named value with given id and value")
@Slf4j
public class GeneratePreprocessedDataSetCommand implements Runnable {

    @Mixin
    private HelpOption helpOption;

    @Option(names = "-words-file-path", description = "Path of input text file with words dictionary", required = true)
    private Path wordsFilePath;
    @Option(names = "-data-set-output-file-path", description = "Path to where preprocessed data set will be stored")
    private Path dataSetOutputFilePath;

    @SneakyThrows
    @Override
    public void run() {
        try (var inputWordsReader = new FileInputWordsReader(wordsFilePath)) {
            var outputDatasetWriter = new FileOutputDatasetWriter(dataSetOutputFilePath);

            var preprocessedDataSetGenerator = new PreprocessedDataSetGenerator(inputWordsReader, outputDatasetWriter);

            preprocessedDataSetGenerator.generate();
        }
    }
}
