package com.dominikcebula.words.generator.application.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WordKeyTest {
    @ParameterizedTest
    @MethodSource("provideTestData")
    void shouldCreateTextRepresentation(String keyValue, String textRepresentation) {
        WordKey wordKey = new WordKey(keyValue);

        assertThat(wordKey.textRepresentation())
                .isEqualTo(textRepresentation);
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("1c1g2i1l1o2s1t", "K 1c1g2i1l1o2s1t"),
                Arguments.of("3e1n1r1u1v", "K 3e1n1r1u1v"),
                Arguments.of("1a1e1g1i1k1m1n1r1t", "K 1a1e1g1i1k1m1n1r1t")
        );
    }
}
