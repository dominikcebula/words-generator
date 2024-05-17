package com.dominikcebula.words.generator.application.domain;

public record WordsMapValue(String value) {
    public String textRepresentation() {
        return "V " + value;
    }
}
