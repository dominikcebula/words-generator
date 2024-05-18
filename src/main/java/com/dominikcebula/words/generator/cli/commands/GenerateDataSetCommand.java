package com.dominikcebula.words.generator.cli.commands;

import com.dominikcebula.words.generator.application.dataset.generator.DataSetGenerator;
import com.dominikcebula.words.generator.cli.common.HelpOption;
import com.dominikcebula.words.generator.io.FileInputReader;
import com.dominikcebula.words.generator.io.FileOutputDatasetWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;

import java.nio.file.Path;

@Command(name = "generate-dataset", description = "Creates pre-generated dataset that contains collection of words that can be created from a given characters")
@Slf4j
public class GenerateDataSetCommand implements Runnable {

    @Mixin
    private HelpOption helpOption;

    @Option(names = "-words-file-path", description = "Path of input text file with words dictionary", required = true)
    private Path wordsFilePath;
    @Option(names = "-data-set-output-file-path", description = "Path to where preprocessed data set will be stored")
    private Path dataSetOutputFilePath;

    @SneakyThrows
    @Override
    public void run() {
        log.info("Generating preprocessed dataset...");

        try (var inputWordsReader = new FileInputReader(wordsFilePath)) {
            var outputDatasetWriter = new FileOutputDatasetWriter(dataSetOutputFilePath);

            var preprocessedDataSetGenerator = new DataSetGenerator(inputWordsReader, outputDatasetWriter);

            preprocessedDataSetGenerator.generate();
        }

        log.info("Finished generating preprocessed dataset.");
    }
}
