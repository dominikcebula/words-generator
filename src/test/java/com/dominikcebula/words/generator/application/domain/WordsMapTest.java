package com.dominikcebula.words.generator.application.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordsMapTest {
    @Test
    void shouldReturnSingleEntry() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordKey("1c1g2i1l1o2s1t"), new WordValue("logistics"));

        assertThat(wordsMap.textRepresentation())
                .isEqualTo("""
                        K 1c1g2i1l1o2s1t
                        V logistics
                        """);
    }

    @Test
    void shouldReturnMultipleEntryForSingleKey() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordKey("1a2b3c"), new WordValue("abbccc"));
        wordsMap.add(new WordKey("1a2b3c"), new WordValue("cccbba"));
        wordsMap.add(new WordKey("1a2b3c"), new WordValue("bbccca"));

        assertThat(wordsMap.textRepresentation())
                .isEqualTo("""
                        K 1a2b3c
                        V abbccc
                        V cccbba
                        V bbccca
                        """);
    }

    @Test
    void shouldReturnSingleKeyAndSingleValue() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordKey("1a"), new WordValue("a"));
        wordsMap.add(new WordKey("2b"), new WordValue("bb"));
        wordsMap.add(new WordKey("3c"), new WordValue("ccc"));

        assertThat(wordsMap.textRepresentation())
                .isEqualTo("""
                        K 1a
                        V a
                        K 2b
                        V bb
                        K 3c
                        V ccc
                        """);
    }

    @Test
    void shouldReturnMultipleValues() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordKey("1a2b"), new WordValue("abb"));
        wordsMap.add(new WordKey("1a2b"), new WordValue("bba"));

        wordsMap.add(new WordKey("1a2c3d"), new WordValue("accddd"));
        wordsMap.add(new WordKey("1a2c3d"), new WordValue("ccaddd"));
        wordsMap.add(new WordKey("1a2c3d"), new WordValue("dddcca"));

        wordsMap.add(new WordKey("1e2f3g4h"), new WordValue("effggghhhh"));
        wordsMap.add(new WordKey("1e2f3g4h"), new WordValue("hhhheffggg"));
        wordsMap.add(new WordKey("1e2f3g4h"), new WordValue("ffeggghhhh"));
        wordsMap.add(new WordKey("1e2f3g4h"), new WordValue("gggeffhhhh"));
        wordsMap.add(new WordKey("1e2f3g4h"), new WordValue("eggghhhhff"));

        assertThat(wordsMap.textRepresentation())
                .isEqualTo("""
                        K 1a2b
                        V abb
                        V bba
                        K 1a2c3d
                        V accddd
                        V ccaddd
                        V dddcca
                        K 1e2f3g4h
                        V effggghhhh
                        V hhhheffggg
                        V ffeggghhhh
                        V gggeffhhhh
                        V eggghhhhff
                        """);
    }

    @Test
    void shouldReturnEmptyStringWhenNoDataAddedToMap() {
        WordsMap wordsMap = new WordsMap();

        assertThat(wordsMap.textRepresentation()).isEmpty();
    }
}