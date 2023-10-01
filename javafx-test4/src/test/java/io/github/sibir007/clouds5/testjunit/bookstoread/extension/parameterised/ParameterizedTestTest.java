package io.github.sibir007.clouds5.testjunit.bookstoread.extension.parameterised;

import io.github.sibir007.clouds5.testjunit.bookstoread.Book;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookFilter;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookPublishedYearFilter;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookShelf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.*;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedTestTest {
    @ParameterizedTest
    @ValueSource(strings = {"Effective Java", "Code Complete", "Clean Code"})
    void shouldGiveBacksBooksForTitle(String title) {
        BookShelf shelf = new BookShelf();
        Book effectiveJava = new Book("Effective Java", "Joshua Bloch",
                LocalDate.of(2008, Month.MAY, 8));
        Book codeComplete = new Book("Code Complete", "Steve McConnel",
                LocalDate.of(2004, Month.JUNE, 9));
        Book mythicalManMonth = new Book("The Mythical Man-Month",
                "Frederick Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
        Book cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.
                of(2008, Month.AUGUST, 1));
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        List<Book> foundBooks = shelf.findBooksByTitle(title.toLowerCase());
        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());
        foundBooks = shelf.findBooksByTitle(title.toUpperCase());
        assertNotNull(foundBooks);
        assertEquals(0, foundBooks.size());
    }

    @ParameterizedTest
    @MethodSource("bookFilterProvider")
    void validateFilterWithNullData(BookFilter bookFilter) {
        assertThat(bookFilter.apply(null)).isFalse();
    }

    static Stream<BookFilter> bookFilterProvider() {
        return Stream.of(BookPublishedYearFilter.Before(2007),
                BookPublishedYearFilter.After(2007));
    }

    @ParameterizedTest
    @ArgumentsSource(BookFilterCompositeArgsProvider.class)
    void validateBookFiltersWithBooks(BookFilter filter, Book[] books) {
        assertNotNull(filter);
        assertFalse(filter.apply(books[0]));
        assertTrue(filter.apply(books[1]));
    }

    @ParameterizedTest(name = "{index} : Validating {1}")
    @DisplayName("Filter validates a passing book")
    @ArgumentsSource(BeforeYearArgsProvider.class)
    @ArgumentsSource(AfterYearArgsProvider.class)

    void validateBookFiltersWithBooks1(BookFilter filter, Book book) {
        assertNotNull(filter);
        assertTrue(filter.apply(book));
    }

}
