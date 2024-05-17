package com.dominikcebula.words.generator.application.domain;

public record WordsMapKey(String value) {
    String textRepresentation() {
        return "K " + value;
    }
}
