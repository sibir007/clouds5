package io.github.sibir007.clouds5.testjunit.bookstoread.extension.template;

import io.github.sibir007.clouds5.testjunit.bookstoread.Book;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookFilter;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

public class BookFilterTemplateSpec {
    @TestTemplate
    @ExtendWith(BookFilterTestInvocationContextProvider.class)
    void validateFilters(BookFilter filter, Book[] books){
        assertNotNull(filter);
        assertFalse(filter.apply(books[0]));
        assertTrue(filter.apply(books[1]));
    }
}
