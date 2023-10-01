package io.github.sibir007.clouds5.testjunit.bookstoread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TimeoutTest {
    @Test
    @DisplayName("assertTimeout with executable")
    void test_should_complete_in_one_second1(){
        assertTimeout(Duration.of(1, ChronoUnit.SECONDS), ()-> Thread.sleep(200));
    }
    @Test
    @DisplayName("assertTimeout с ThrowingSupplier")
    void test_should_complete_in_one_second2(){
        String mes = assertTimeout(Duration.of(1, ChronoUnit.SECONDS), ()-> "Hello World");
        assertEquals("Hello World", mes);
    }

    @Test
    @DisplayName("assertTimeoutPreemptively")
    void test_should_complete_in_one_second3(){
        assertTimeoutPreemptively(Duration.of(1, ChronoUnit.SECONDS), ()-> Thread.sleep(200));
    }
}
