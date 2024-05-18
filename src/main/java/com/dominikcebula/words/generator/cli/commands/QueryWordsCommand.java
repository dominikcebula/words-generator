package com.dominikcebula.words.generator.cli.commands;

import com.dominikcebula.words.generator.application.dataset.loader.DataSetLoader;
import com.dominikcebula.words.generator.application.query.WordsQuery;
import com.dominikcebula.words.generator.application.words.map.WordsMap;
import com.dominikcebula.words.generator.io.FileInputReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;

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
            var dataSetLoader = new DataSetLoader(inputDataSetReader);
            WordsMap wordsMap = dataSetLoader.load();
            log.info("Loaded preprocessed words dataset.");

            log.info("Querying for possible words...");
            var wordsQuery = new WordsQuery(wordsMap);
            List<String> possibleWords = wordsQuery.queryPossibleWords(characters);
            log.info("Finished Querying for possible words.");

            possibleWords.forEach(System.out::println);
        }
    }
}
