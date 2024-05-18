package com.dominikcebula.words.generator.application.domain;

public record WordValue(String value) {
    public String textRepresentation() {
        return "V " + value;
    }
}
