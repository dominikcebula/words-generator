package com.dominikcebula.words.generator.application.words.map.value;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordValueTest {
    @ParameterizedTest
    @MethodSource("wordValuesWithLineValues")
    void shouldCreateLineValue(String wordSetValueString, String lineValue) {
        WordValue wordValue = new WordValue(wordSetValueString);

        assertThat(wordValue.toLineValue())
                .isEqualTo(lineValue);
    }

    @ParameterizedTest
    @MethodSource("wordValuesWithLineValues")
    void shouldCreateWordValueFromLineValue(String keyValue, String lineValue) {
        WordValue wordValue = WordValue.fromLineValue(lineValue);

        assertThat(wordValue.value())
                .isEqualTo(keyValue);
    }

    @ParameterizedTest
    @MethodSource("wordValueLineValues")
    void shouldDetectWordValue(String lineValue, boolean expectedIsLineValue) {
        assertThat(WordValue.isWordValueLine(lineValue))
                .isEqualTo(expectedIsLineValue);
    }

    @Test
    void shouldThrowExceptionWhenTryingToCreateWordValueFromIncorrectLineValue() {
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> WordValue.fromLineValue("abc"));

        assertThat(thrownException.getMessage())
                .isEqualTo("Cannot create word value from line value abc");
    }

    private static Stream<Arguments> wordValuesWithLineValues() {
        return Stream.of(
                Arguments.of("compliance", "V compliance"),
                Arguments.of("scalability", "V scalability"),
                Arguments.of("logistics", "V logistics")
        );
    }

    private static Stream<Arguments> wordValueLineValues() {
        return Stream.of(
                Arguments.of("V compliance", true),
                Arguments.of("V scalability", true),
                Arguments.of("V logistics", true),
                Arguments.of("Vlogistics", false),
                Arguments.of("logistics", false),
                Arguments.of(" ", false),
                Arguments.of("", false)
        );
    }
}