package com.dominikcebula.words.generator.application.query;

import com.dominikcebula.words.generator.application.words.map.WordsMap;
import com.dominikcebula.words.generator.application.words.map.key.WordKey;
import com.dominikcebula.words.generator.application.words.map.value.WordValue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WordsQueryTest {
    @Test
    void shouldReturnMultiplePossibleWords() {
        WordsMap wordsMap = createWordsMap();
        WordsQuery wordsQuery = new WordsQuery(wordsMap);

        List<String> possibleWords = wordsQuery.queryPossibleWords("cgiilosst");

        assertThat(possibleWords)
                .containsExactly("logistics", "scalability", "compliance");
    }

    @Test
    void shouldReturnSinglePossibleWord() {
        WordsMap wordsMap = createWordsMap();
        WordsQuery wordsQuery = new WordsQuery(wordsMap);

        List<String> possibleWords = wordsQuery.queryPossibleWords("acehinsst");

        assertThat(possibleWords)
                .containsExactly("asthenics");
    }

    @Test
    void shouldReturnNoPossibleWords() {
        WordsMap wordsMap = createWordsMap();
        WordsQuery wordsQuery = new WordsQuery(wordsMap);

        List<String> possibleWords = wordsQuery.queryPossibleWords("abcxyz");

        assertThat(possibleWords)
                .isEmpty();
    }

    private WordsMap createWordsMap() {
        WordsMap wordsMap = new WordsMap();

        WordKey wordKey1 = new WordKey("1c1g2i1l1o2s1t");
        WordValue wordValue11 = new WordValue("logistics");
        WordValue wordValue12 = new WordValue("scalability");
        WordValue wordValue13 = new WordValue("compliance");

        WordKey wordKey2 = new WordKey("1a1c1e1h1i1n1s1t");
        WordValue wordValue21 = new WordValue("asthenic");
        WordValue wordValue22 = new WordValue("chanties");

        WordKey wordKey3 = new WordKey("1a1c1e1h1i1n2s1t");
        WordValue wordValue31 = new WordValue("asthenics");


        wordsMap.add(wordKey1, wordValue11);
        wordsMap.add(wordKey1, wordValue12);
        wordsMap.add(wordKey1, wordValue13);

        wordsMap.add(wordKey2, wordValue21);
        wordsMap.add(wordKey2, wordValue22);

        wordsMap.add(wordKey3, wordValue31);
        return wordsMap;
    }
}