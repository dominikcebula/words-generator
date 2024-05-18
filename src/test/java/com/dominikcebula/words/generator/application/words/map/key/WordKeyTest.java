package com.dominikcebula.words.generator.application.words.map.key;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordKeyTest {
    @ParameterizedTest
    @MethodSource("wordKeysWithLineValues")
    void shouldCreateLineValue(String keyValue, String lineValue) {
        WordKey wordKey = new WordKey(keyValue);

        assertThat(wordKey.toLineValue())
                .isEqualTo(lineValue);
    }

    @ParameterizedTest
    @MethodSource("wordKeysWithLineValues")
    void shouldCreateWordKeyFromLineValue(String keyValue, String lineValue) {
        WordKey wordKey = WordKey.fromLineValue(lineValue);

        assertThat(wordKey.value())
                .isEqualTo(keyValue);
    }

    @ParameterizedTest
    @MethodSource("wordKeyLineValues")
    void shouldDetectWordKey(String lineValue, boolean expectedIsLineValue) {
        assertThat(WordKey.isWordKeyLine(lineValue))
                .isEqualTo(expectedIsLineValue);
    }

    @Test
    void shouldThrowExceptionWhenTryingToCreateWordKeyFromIncorrectLineValue() {
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> WordKey.fromLineValue("abc"));

        assertThat(thrownException.getMessage())
                .isEqualTo("Cannot create word key from line value abc");
    }

    private static Stream<Arguments> wordKeysWithLineValues() {
        return Stream.of(
                Arguments.of("1c1g2i1l1o2s1t", "K 1c1g2i1l1o2s1t"),
                Arguments.of("3e1n1r1u1v", "K 3e1n1r1u1v"),
                Arguments.of("1a1e1g1i1k1m1n1r1t", "K 1a1e1g1i1k1m1n1r1t")
        );
    }

    private static Stream<Arguments> wordKeyLineValues() {
        return Stream.of(
                Arguments.of("K 1c1g2i1l1o2s1t", true),
                Arguments.of("K 3e1n1r1u1v", true),
                Arguments.of("K 1a1e1g1i1k1m1n1r1t", true),
                Arguments.of("K1c1g2i1l1o2s1t", false),
                Arguments.of("1c1g2i1l1o2s1t", false),
                Arguments.of(" ", false),
                Arguments.of("", false)
        );
    }
}
