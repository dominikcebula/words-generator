package com.dominikcebula.words.generator.cli;

import java.util.Optional;

import static com.dominikcebula.words.generator.cli.Application.ExitHandler;

public class MockExitHandler implements ExitHandler {
    private Integer exitCode;

    @Override
    public void execute(int exitCode) {
        this.exitCode = exitCode;
    }

    public Optional<Integer> getExitCode() {
        return Optional.ofNullable(exitCode);
    }
}
