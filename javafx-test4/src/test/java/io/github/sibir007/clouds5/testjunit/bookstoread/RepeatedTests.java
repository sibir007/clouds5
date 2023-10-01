package io.github.sibir007.clouds5.testjunit.bookstoread;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepeatedTests {
    @RepeatedTest(5)
      void i_am_a_repeated_test1(){
        assertTrue(true);
    }

    @RepeatedTest(value = 5, name = "i_am_a_repeated_test__{currentRepetition}/{totalRepetitions}")
    void i_am_a_repeated_test2(){
        assertTrue(true);
    }
}
