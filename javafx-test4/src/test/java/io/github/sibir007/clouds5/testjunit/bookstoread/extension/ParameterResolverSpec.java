package io.github.sibir007.clouds5.testjunit.bookstoread.extension;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

//JUnit 5 bundles the following three ParameterResolvers:
//1.TestInfoParameterResolver
//2.TestReporterParameterResolver
//3.RepetitionInfoParameterResolver
public class ParameterResolverSpec {
    @BeforeEach
    void initialize(TestInfo testInfo, TestReporter reporter) {
        reporter.publishEntry("Associated tags: ", testInfo.getTags().toString());
    }

    @RepeatedTest(value = 10)
    @Tag("Numbers")
    void numberTest(RepetitionInfo info) {
        System.out.println(String.format("current repetition %d/%d", info.getCurrentRepetition(), info.getTotalRepetitions()));
        assertTrue(true);
    }

    @RepeatedTest(value = 10)
    void nonRepeated(TestInfo info, RepetitionInfo repetitionInfo) {
        assertTrue(true);
    }
}
