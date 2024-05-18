package com.dominikcebula.words.generator.cli;

import org.apache.commons.io.output.TeeOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.Files.readString;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @TempDir
    private Path tempDir;

    private Application application;
    private MockExitHandler exitHandler;

    private ByteArrayOutputStream outputCapture;
    private PrintStream systemOut;

    @BeforeEach
    public void setUp() {
        setUpApplication();
        setUpCaptureOutput();
    }

    @AfterEach
    public void tearDown() throws IOException {
        tearDownCaptureOutput();
    }

    @Test
    void shouldGenerateDataSet() throws IOException {
        String wordsAlphaSamplePath = getWordsAlphaSamplePath();
        String datasetOutputPath = getDatasetOutputPath();

        runWithArgs("generate-dataset -words-file-path " + wordsAlphaSamplePath + " -data-set-output-file-path " + datasetOutputPath);

        assertProducedDataSetExists(datasetOutputPath);
        assertProducedDataSetContent(datasetOutputPath, getWordsAlphaSampleDataContent());
        assertExitCode(0);
    }

    @Test
    void shouldQueryForPossibleWords() {
        runWithArgs("query-words -data-set-file-path " + getWordsAlphaSampleDataPath() + " -characters abbdelr");

        assertCapturedOutputContains(
                """
                        dabbler
                        drabble
                        rabbled
                        """);
        assertExitCode(0);
    }

    private void setUpApplication() {
        exitHandler = new MockExitHandler();
        application = new Application(exitHandler);
    }

    private void setUpCaptureOutput() {
        outputCapture = new ByteArrayOutputStream();
        systemOut = System.out;
        systemOut.flush();
        System.setOut(new PrintStream(new TeeOutputStream(
                systemOut,
                outputCapture
        )));
    }

    private void tearDownCaptureOutput() throws IOException {
        System.setOut(systemOut);
        outputCapture.close();
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

    private void assertProducedDataSetExists(String datasetOutputPath) {
        assertThat(Files.exists(Path.of(datasetOutputPath)))
                .withFailMessage(() -> "Expected output dataset to exists under path: " + datasetOutputPath)
                .isTrue();
    }

    private void assertProducedDataSetContent(String datasetOutputPath, String expectedContent) throws IOException {
        assertThat(readString(Path.of(datasetOutputPath)))
                .isEqualTo(expectedContent);
    }

    private void assertCapturedOutputContains(String expectedContent) {
        assertThat(outputCapture.toString())
                .contains(expectedContent);
    }

    private void assertExitCode(int expectedExitCode) {
        assertThat(exitHandler.getExitCode())
                .isPresent()
                .isEqualTo(Optional.of(expectedExitCode));
    }
}