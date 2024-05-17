package com.dominikcebula.words.generator.cli.commands;

import com.dominikcebula.words.generator.cli.common.HelpOption;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;

@Command(name = "create", description = "Creates named value with given id and value")
@Slf4j
public class GeneratePreprocessedDataSetCommand implements Runnable {

    @Mixin
    private HelpOption helpOption;

    @Option(names = "-words-file-path", description = "Path of input text file with words dictionary", required = true)
    private String wordsFilePath;
    @Option(names = "-data-set-output-file-path", description = "Path to where preprocessed data set will be stored")
    private String dataSetOutputFilePath;

    @Override
    public void run() {
        // TODO
    }
}
