package com.dominikcebula.words.generator.application.domain;

public record WordKey(String value) {
    String textRepresentation() {
        return "K " + value;
    }
}
