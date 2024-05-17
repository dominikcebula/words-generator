package com.dominikcebula.words.generator.application.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordsMapTest {
    @Test
    void shouldReturnSingleEntry() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordsMapKey("1c1g2i1l1o2s1t"), new WordsMapValue("logistics"));

        assertThat(wordsMap.textRepresentation())
                .isEqualTo("""
                        K 1c1g2i1l1o2s1t
                        V logistics
                        """);
    }

    @Test
    void shouldReturnMultipleEntryForSingleKey() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordsMapKey("1a2b3c"), new WordsMapValue("abbccc"));
        wordsMap.add(new WordsMapKey("1a2b3c"), new WordsMapValue("cccbba"));
        wordsMap.add(new WordsMapKey("1a2b3c"), new WordsMapValue("bbccca"));

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

        wordsMap.add(new WordsMapKey("1a"), new WordsMapValue("a"));
        wordsMap.add(new WordsMapKey("2b"), new WordsMapValue("bb"));
        wordsMap.add(new WordsMapKey("3c"), new WordsMapValue("ccc"));

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

        wordsMap.add(new WordsMapKey("1a2b"), new WordsMapValue("abb"));
        wordsMap.add(new WordsMapKey("1a2b"), new WordsMapValue("bba"));

        wordsMap.add(new WordsMapKey("1a2c3d"), new WordsMapValue("accddd"));
        wordsMap.add(new WordsMapKey("1a2c3d"), new WordsMapValue("ccaddd"));
        wordsMap.add(new WordsMapKey("1a2c3d"), new WordsMapValue("dddcca"));

        wordsMap.add(new WordsMapKey("1e2f3g4h"), new WordsMapValue("effggghhhh"));
        wordsMap.add(new WordsMapKey("1e2f3g4h"), new WordsMapValue("hhhheffggg"));
        wordsMap.add(new WordsMapKey("1e2f3g4h"), new WordsMapValue("ffeggghhhh"));
        wordsMap.add(new WordsMapKey("1e2f3g4h"), new WordsMapValue("gggeffhhhh"));
        wordsMap.add(new WordsMapKey("1e2f3g4h"), new WordsMapValue("eggghhhhff"));

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