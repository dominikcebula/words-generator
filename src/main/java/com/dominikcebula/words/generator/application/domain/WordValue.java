package com.dominikcebula.words.generator.application.domain;

public record WordValue(String value) {

    private static final String VALUE_PREFIX = "V ";

    public static boolean isWordValue(String value) {
        return value.startsWith(VALUE_PREFIX);
    }

    public static WordValue fromLineValue(String lineValue) {
        if (!isWordValue(lineValue))
            throw new IllegalArgumentException("Cannot create word value from line value " + lineValue);

        return new WordValue(lineValue.substring(VALUE_PREFIX.length()));
    }

    public String textRepresentation() {
        return VALUE_PREFIX + value;
    }
}
