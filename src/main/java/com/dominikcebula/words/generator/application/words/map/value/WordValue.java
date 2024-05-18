package com.dominikcebula.words.generator.application.words.map.value;

public record WordValue(String value) {

    private static final String VALUE_PREFIX = "V ";

    public static boolean isWordValueLine(String lineValue) {
        return lineValue.startsWith(VALUE_PREFIX);
    }

    public static WordValue fromLineValue(String lineValue) {
        if (!isWordValueLine(lineValue))
            throw new IllegalArgumentException("Cannot create word value from line value " + lineValue);

        return new WordValue(lineValue.substring(VALUE_PREFIX.length()));
    }

    public String toLineValue() {
        return VALUE_PREFIX + value;
    }
}
