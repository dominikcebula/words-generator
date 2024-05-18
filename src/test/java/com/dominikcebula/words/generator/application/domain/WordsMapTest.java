package com.dominikcebula.words.generator.application.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordsMapTest {
    @Test
    void shouldReturnSingleValue() {
        WordKey wordKey = new WordKey("1c1g2i1l1o2s1t");
        WordValue wordValue = new WordValue("logistics");
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(wordKey, wordValue);

        assertThat(wordsMap.get(wordKey).getWordValues())
                .containsOnly(wordValue);
    }

    @Test
    void shouldReturnMultipleValuesForSingleKey() {
        WordKey wordKey = new WordKey("1c1g2i1l1o2s1t");
        WordValue wordValue1 = new WordValue("logistics");
        WordValue wordValue2 = new WordValue("scalability");
        WordValue wordValue3 = new WordValue("compliance");
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(wordKey, wordValue1);
        wordsMap.add(wordKey, wordValue2);
        wordsMap.add(wordKey, wordValue3);

        assertThat(wordsMap.get(wordKey).getWordValues())
                .containsOnly(wordValue1, wordValue2, wordValue3);
    }

    @Test
    void shouldReturnMultipleValuesForMultipleKeys() {
        WordKey wordKey1 = new WordKey("1c1g2i1l1o2s1t");
        WordValue wordValue11 = new WordValue("logistics");
        WordValue wordValue12 = new WordValue("scalability");
        WordValue wordValue13 = new WordValue("compliance");

        WordKey wordKey2 = new WordKey("1a1c1e1h1i1n1s1t");
        WordValue wordValue21 = new WordValue("asthenic");
        WordValue wordValue22 = new WordValue("chanties");

        WordKey wordKey3 = new WordKey("1a1c1e1h1i1n2s1t");
        WordValue wordValue31 = new WordValue("asthenics");

        WordsMap wordsMap = new WordsMap();

        wordsMap.add(wordKey1, wordValue11);
        wordsMap.add(wordKey1, wordValue12);
        wordsMap.add(wordKey1, wordValue13);

        wordsMap.add(wordKey2, wordValue21);
        wordsMap.add(wordKey2, wordValue22);

        wordsMap.add(wordKey3, wordValue31);

        assertThat(wordsMap.get(wordKey1).getWordValues())
                .containsExactly(wordValue11, wordValue12, wordValue13);
        assertThat(wordsMap.get(wordKey2).getWordValues())
                .containsExactly(wordValue21, wordValue22);
        assertThat(wordsMap.get(wordKey3).getWordValues())
                .containsExactly(wordValue31);
    }

    @Test
    void shouldReturnEmptySetForNonExistingKey() {
        WordKey wordKey = new WordKey("1c1g2i1l1o2s1t");
        WordsMap wordsMap = new WordsMap();

        assertThat(wordsMap.get(wordKey).getWordValues())
                .isEmpty();
    }

    @Test
    void shouldReturnSingleLineValue() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordKey("1c1g2i1l1o2s1t"), new WordValue("logistics"));

        assertThat(wordsMap.toLinesValues())
                .isEqualTo("""
                        K 1c1g2i1l1o2s1t
                        V logistics
                        """);
    }

    @Test
    void shouldReturnMultipleLinesValuesForSingleKey() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordKey("1a2b3c"), new WordValue("abbccc"));
        wordsMap.add(new WordKey("1a2b3c"), new WordValue("cccbba"));
        wordsMap.add(new WordKey("1a2b3c"), new WordValue("bbccca"));

        assertThat(wordsMap.toLinesValues())
                .isEqualTo("""
                        K 1a2b3c
                        V abbccc
                        V cccbba
                        V bbccca
                        """);
    }

    @Test
    void shouldReturnSingleKeyAndSingleValueLinesValues() {
        WordsMap wordsMap = new WordsMap();

        wordsMap.add(new WordKey("1a"), new WordValue("a"));
        wordsMap.add(new WordKey("2b"), new WordValue("bb"));
        wordsMap.add(new WordKey("3c"), new WordValue("ccc"));

        assertThat(wordsMap.toLinesValues())
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
    void shouldReturnMultipleLinesValues() {
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

        assertThat(wordsMap.toLinesValues())
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

        assertThat(wordsMap.toLinesValues()).isEmpty();
    }
}