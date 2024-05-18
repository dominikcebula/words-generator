package com.dominikcebula.words.generator.application.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WordValueTest {
    @ParameterizedTest
    @MethodSource("provideTestData")
    void shouldCreateTextRepresentation(String wordSetValueString, String textRepresentation) {
        WordValue wordValue = new WordValue(wordSetValueString);

        assertThat(wordValue.textRepresentation())
                .isEqualTo(textRepresentation);
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("compliance", "V compliance"),
                Arguments.of("scalability", "V scalability"),
                Arguments.of("logistics", "V logistics")
        );
    }
}