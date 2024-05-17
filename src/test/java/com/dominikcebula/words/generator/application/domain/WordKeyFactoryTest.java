package com.dominikcebula.words.generator.application.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WordKeyFactoryTest {

    private final WordKeyFactory wordKeyFactory = new WordKeyFactory();

    @ParameterizedTest
    @MethodSource("provideWordKeyTestData")
    void shouldCreateWordKey(String inputWord, String expectedWordKey) {
        WordKey wordKey = wordKeyFactory.createFromWord(inputWord);

        assertThat(wordKey.value()).isEqualTo(expectedWordKey);
    }

    private static Stream<Arguments> provideWordKeyTestData() {
        return Stream.of(
                Arguments.of("logistics", "1c1g2i1l1o2s1t"),
                Arguments.of("revenue", "3e1n1r1u1v"),
                Arguments.of("marketing", "1a1e1g1i1k1m1n1r1t"),
                Arguments.of("strategy", "1a1e1g1r1s2t1y"),
                Arguments.of("investment", "2e1i1m2n1s2t1v"),
                Arguments.of("profitability", "1a1b1f3i1l1o1p1r2t1y"),
                Arguments.of("entrepreneurship", "4e1h1i2n2p3r1s1t1u"),
                Arguments.of("stakeholder", "1a1d2e1h1k1l1o1r1s1t"),
                Arguments.of("merger", "2e1g1m2r"),
                Arguments.of("innovation", "1a2i3n2o1t1v")
        );
    }
}