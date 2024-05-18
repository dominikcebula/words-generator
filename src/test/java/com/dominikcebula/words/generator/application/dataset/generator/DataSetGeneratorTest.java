package com.dominikcebula.words.generator.application.dataset.generator;

import com.dominikcebula.words.generator.io.InMemoryInputReader;
import com.dominikcebula.words.generator.io.InMemoryOutputDatasetWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataSetGeneratorTest {
    private DataSetGenerator dataSetGenerator;
    private InMemoryInputReader inMemoryInputReader;
    private InMemoryOutputDatasetWriter inMemoryOutputDatasetWriter;

    @BeforeEach
    public void setUp() {
        inMemoryInputReader = new InMemoryInputReader();
        inMemoryOutputDatasetWriter = new InMemoryOutputDatasetWriter();
        dataSetGenerator = new DataSetGenerator(inMemoryInputReader, inMemoryOutputDatasetWriter);
    }

    @Test
    void shouldGenerateDataSetForOneWord() {
        inMemoryInputReader.setData(Collections.singletonList("revenue"));

        dataSetGenerator.generate();

        assertThat(inMemoryOutputDatasetWriter.getContent())
                .isEqualTo("""
                        K 3e1n1r1u1v
                        V revenue
                        """);
    }

    @Test
    void shouldGenerateDataSetForMultipleWords() {
        inMemoryInputReader.setData(List.of(
                "revenue",
                "marketing",
                "sale",
                "seal",
                "lase",
                "strategy",
                "investment",
                "profitability",
                "earn",
                "near",
                "rane"
        ));

        dataSetGenerator.generate();

        assertThat(inMemoryOutputDatasetWriter.getContent())
                .isEqualTo("""
                        K 3e1n1r1u1v
                        V revenue
                        K 1a1e1g1i1k1m1n1r1t
                        V marketing
                        K 1a1e1l1s
                        V sale
                        V seal
                        V lase
                        K 1a1e1g1r1s2t1y
                        V strategy
                        K 2e1i1m2n1s2t1v
                        V investment
                        K 1a1b1f3i1l1o1p1r2t1y
                        V profitability
                        K 1a1e1n1r
                        V earn
                        V near
                        V rane
                        """);
    }

    @Test
    void shouldDoNothingForEmptyInput() {
        inMemoryInputReader.setData(Collections.emptyList());

        dataSetGenerator.generate();

        assertThat(inMemoryOutputDatasetWriter.getContent())
                .isEmpty();
    }
}