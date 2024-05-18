package com.dominikcebula.words.generator.cli.io;

import com.dominikcebula.words.generator.application.words.generator.port.OutputDataDisplay;

public class ConsoleOutputDataDisplay implements OutputDataDisplay {
    @Override
    public void displayLine(String line) {
        System.out.println(line);
    }
}
