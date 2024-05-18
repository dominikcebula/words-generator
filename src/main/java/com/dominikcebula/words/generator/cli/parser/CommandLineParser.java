package com.dominikcebula.words.generator.cli.parser;

import com.dominikcebula.words.generator.cli.commands.GenerateDataSetCommand;
import com.dominikcebula.words.generator.cli.commands.QueryWordsCommand;
import com.dominikcebula.words.generator.cli.common.HelpOption;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

import static picocli.CommandLine.Option;

@Command(
        subcommands = {GenerateDataSetCommand.class, QueryWordsCommand.class},
        versionProvider = VersionProvider.class
)
public class CommandLineParser {

    @Mixin
    private HelpOption helpOption;
    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionHelpRequested;
}
