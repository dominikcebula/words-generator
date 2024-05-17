package com.dominikcebula.words.generator.cli.parser;

import com.dominikcebula.words.generator.cli.commands.CreateCommand;
import com.dominikcebula.words.generator.cli.commands.DeleteCommand;
import com.dominikcebula.words.generator.cli.commands.ListCommand;
import com.dominikcebula.words.generator.cli.commands.UpdateCommand;
import com.dominikcebula.words.generator.cli.common.HelpOption;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

import static picocli.CommandLine.Option;

@Command(
        subcommands = {CreateCommand.class, ListCommand.class, UpdateCommand.class, DeleteCommand.class},
        versionProvider = VersionProvider.class
)
public class CommandLineParser {

    @Mixin
    private HelpOption helpOption;
    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionHelpRequested;
}
