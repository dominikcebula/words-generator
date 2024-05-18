package com.dominikcebula.words.generator.application.domain;

public record WordKey(String value) {
    private static final String KEY_PREFIX = "K ";

    public static boolean isWordKeyLine(String lineValue) {
        return lineValue.startsWith(KEY_PREFIX);
    }

    public static WordKey fromLineValue(String lineValue) {
        if (!isWordKeyLine(lineValue))
            throw new IllegalArgumentException("Cannot create word key from line value " + lineValue);

        return new WordKey(lineValue.substring(KEY_PREFIX.length()));
    }

    String toLineValue() {
        return KEY_PREFIX + value;
    }
}
