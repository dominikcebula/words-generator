package com.dominikcebula.words.generator.cli.commands;

import com.dominikcebula.words.generator.application.dataset.loader.PreprocessedDataSetLoader;
import com.dominikcebula.words.generator.application.domain.WordsMap;
import com.dominikcebula.words.generator.application.words.generator.WordsGenerator;
import com.dominikcebula.words.generator.cli.io.ConsoleOutputDataDisplay;
import com.dominikcebula.words.generator.io.FileInputReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import java.nio.file.Path;

@CommandLine.Command(name = "query-words", description = "Queries for possible words that can be created from a given set of characters")
@Slf4j
public class QueryWordsCommand implements Runnable {
    @CommandLine.Option(names = "-data-set-file-path", description = "Path to where preprocessed data set is stored")
    private Path dataSetFilePath;
    @CommandLine.Option(names = "-characters", description = "Characters available to build possible words")
    private String characters;

    @SneakyThrows
    @Override
    public void run() {
        log.info("Loading preprocessed words dataset...");
        try (var inputDataSetReader = new FileInputReader(dataSetFilePath)) {
            var preprocessedDataSetLoader = new PreprocessedDataSetLoader(inputDataSetReader);
            WordsMap wordsMap = preprocessedDataSetLoader.load();
            log.info("Loaded preprocessed words dataset.");

            log.info("Querying for possible words...");
            var consoleOutputDataDisplay = new ConsoleOutputDataDisplay();
            var wordsGenerator = new WordsGenerator(wordsMap, consoleOutputDataDisplay);
            wordsGenerator.generatePossibleWords(characters);
            log.info("Finished Querying for possible words.");
        }
    }
}
