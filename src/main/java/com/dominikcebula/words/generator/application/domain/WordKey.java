package com.dominikcebula.words.generator.application.domain;

public record WordKey(String value) {
    private static final String KEY_PREFIX = "K ";

    public static boolean isWordKey(String value) {
        return value.startsWith(KEY_PREFIX);
    }

    public static WordKey fromLineValue(String lineValue) {
        if (!isWordKey(lineValue))
            throw new IllegalArgumentException("Cannot create word key from line value " + lineValue);

        return new WordKey(lineValue.substring(KEY_PREFIX.length()));
    }

    String textRepresentation() {
        return KEY_PREFIX + value;
    }
}
