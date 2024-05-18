package com.dominikcebula.words.generator.application.words.map.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordsSetTest {
    private static final WordValue WORD_1 = new WordValue("compliance");
    private static final WordValue WORD_2 = new WordValue("scalability");
    private static final WordValue WORD_3 = new WordValue("logistics");

    private WordsSet wordsSet;

    @BeforeEach
    public void setUp() {
        wordsSet = new WordsSet();
    }

    @Test
    void shouldAddSingleWord() {
        wordsSet.add(WORD_1);

        assertThat(wordsSet.getWordValues())
                .containsExactly(WORD_1);
    }

    @Test
    void shouldAddMultipleWords() {
        wordsSet.add(WORD_1);
        wordsSet.add(WORD_2);
        wordsSet.add(WORD_3);

        assertThat(wordsSet.getWordValues())
                .containsExactly(WORD_1, WORD_2, WORD_3);
    }

    @Test
    void shouldBeEmptyByDefault() {
        assertThat(wordsSet.getWordValues())
                .isEmpty();
    }

    @Test
    void shouldReturnEmptyLineValue() {
        assertThat(wordsSet.toLineValue())
                .isEmpty();
    }

    @Test
    void shouldReturnSingleLineValue() {
        wordsSet.add(WORD_1);

        assertThat(wordsSet.toLineValue())
                .isEqualTo("V compliance\n");
    }

    @Test
    void shouldReturnMultipleLineValues() {
        wordsSet.add(WORD_1);
        wordsSet.add(WORD_2);
        wordsSet.add(WORD_3);

        assertThat(wordsSet.toLineValue())
                .isEqualTo("""
                        V compliance
                        V scalability
                        V logistics
                        """);
    }
}
