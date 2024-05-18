package com.dominikcebula.words.generator.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.Files.readString;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @TempDir
    private Path tempDir;

    private MockExitHandler exitHandler;
    private Application application;

    @BeforeEach
    public void setUp() {
        exitHandler = new MockExitHandler();
        application = new Application(exitHandler);
    }

    @Test
    void shouldGenerateDataSet() throws IOException {
        String wordsAlphaSamplePath = getWordsAlphaSamplePath();
        String datasetOutputPath = getDatasetOutputPath();

        runWithArgs("generate-dataset -words-file-path " + wordsAlphaSamplePath + " -data-set-output-file-path " + datasetOutputPath);

        assertProducedDataSetContent(datasetOutputPath, getWordsAlphaSampleDataContent());
        assertExitCode(0);
    }

    private String getWordsAlphaSamplePath() {
        return ApplicationTest.class.getResource("/words_alpha_sample.txt")
                .getFile();
    }

    private String getWordsAlphaSampleDataContent() throws IOException {
        return Files.readString(Path.of(getWordsAlphaSampleDataPath()));
    }

    private String getWordsAlphaSampleDataPath() {
        return ApplicationTest.class.getResource("/words_alpha_sample_dataset.txt")
                .getFile();
    }

    private String getDatasetOutputPath() {
        return tempDir.resolve("words_alpha_sample_dataset.txt").toFile().getAbsolutePath();
    }

    private void runWithArgs(String arguments) {
        application.execute(arguments.split(" "));
    }

    private void assertProducedDataSetContent(String datasetOutputPath, String expectedContent) throws IOException {
        assertThat(readString(Path.of(datasetOutputPath)))
                .isEqualTo(expectedContent);
    }

    private void assertExitCode(int expectedExitCode) {
        assertThat(exitHandler.getExitCode())
                .isPresent()
                .isEqualTo(Optional.of(expectedExitCode));
    }
}