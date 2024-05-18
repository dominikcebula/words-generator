package com.dominikcebula.words.generator.cli.commands;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import java.nio.file.Path;

@CommandLine.Command(name = "generate-words", description = "Generates possible words from given set of characters")
@Slf4j
public class GenerateWordsCommand implements Runnable {
    @CommandLine.Option(names = "-data-set-file-path", description = "Path to where preprocessed data set is stored")
    private Path dataSetFilePath;
    @CommandLine.Option(names = "-characters", description = "Characters available to build possible words")
    private String characters;

    @Override
    public void run() {
        log.info("Generating possible words...");


    }
}
