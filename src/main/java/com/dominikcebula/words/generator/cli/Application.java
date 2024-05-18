package com.dominikcebula.words.generator.cli;

import com.dominikcebula.words.generator.cli.parser.CommandLineParser;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine;

@RequiredArgsConstructor
public class Application {

    private final ExitHandler exitHandler;

    public static void main(String[] args) {
        new Application(new SystemExitHandler()).execute(args);
    }

    void execute(String[] args) {
        CommandLine commandLine = new CommandLine(new CommandLineParser());

        if (args.length == 0) {
            commandLine.usage(System.out);
            exitHandler.execute(1);
        }

        int exitCode = commandLine.execute(args);
        exitHandler.execute(exitCode);
    }

    interface ExitHandler {
        void execute(int exitCode);
    }

    private static class SystemExitHandler implements ExitHandler {
        @Override
        public void execute(int exitCode) {
            System.exit(exitCode);
        }
    }
}
