package com.dominikcebula.words.generator.application.dataset.loader;

import com.dominikcebula.words.generator.application.domain.WordKey;
import com.dominikcebula.words.generator.application.domain.WordValue;
import com.dominikcebula.words.generator.application.domain.WordsMap;
import com.dominikcebula.words.generator.application.domain.WordsSet;
import com.dominikcebula.words.generator.io.InMemoryInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PreprocessedDataSetLoaderTest {
    private InMemoryInputReader inputReader;
    private PreprocessedDataSetLoader dataSetLoader;

    @BeforeEach
    public void setUp() {
        inputReader = new InMemoryInputReader();
        dataSetLoader = new PreprocessedDataSetLoader(inputReader);
    }

    @Test
    void shouldLoadData() {
        inputReader.setData(List.of(
                "K 1a1b1c1j1n1t1u",
                "V abjunct",
                "K 1a1b1l",
                "V abl",
                "V alb",
                "V bal",
                "K 2a1b1e1l1r",
                "V ablare",
                "V arable"
        ));

        WordsMap wordsMap = dataSetLoader.load();

        assertThat(wordsMap.toLinesValues())
                .isEqualTo("""
                        K 1a1b1c1j1n1t1u
                        V abjunct
                        K 1a1b1l
                        V abl
                        V alb
                        V bal
                        K 2a1b1e1l1r
                        V ablare
                        V arable
                        """);
    }

    @Test
    void shouldHaveQueryableData() {
        inputReader.setData(List.of(
                "K 1a1b1c1j1n1t1u",
                "V abjunct",
                "K 1a1b1l",
                "V abl",
                "V alb",
                "V bal"
        ));

        WordsMap wordsMap = dataSetLoader.load();

        WordsSet wordsSet1 = wordsMap.get(new WordKey("1a1b1c1j1n1t1u"));
        WordsSet wordsSet2 = wordsMap.get(new WordKey("1a1b1l"));
        WordsSet wordsSet3 = wordsMap.get(new WordKey("2a1b1e1l1t"));

        assertThat(wordsSet1.getWordValues())
                .containsOnly(
                        new WordValue("abjunct")
                );
        assertThat(wordsSet2.getWordValues())
                .containsExactly(
                        new WordValue("abl"),
                        new WordValue("alb"),
                        new WordValue("bal")
                );
        assertThat(wordsSet3.getWordValues())
                .isEmpty();
    }
}