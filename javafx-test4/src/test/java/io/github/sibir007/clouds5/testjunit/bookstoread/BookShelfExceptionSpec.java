package io.github.sibir007.clouds5.testjunit.bookstoread;

import io.github.sibir007.clouds5.testjunit.bookstoread.exceptions.BookShelfCapacityReachedException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf exceptions")
@ExtendWith(BooksParameterResolver.class)
@ExtendWith(LoggingTestExecutionExceptionHandler.class)
public class BookShelfExceptionSpec {
    private BookShelf shelf = new BookShelf();
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init(Map<String, Book> books) throws Exception {

//        this.effectiveJava = new Book("Effective Java", "Joshua Bloch",
//                LocalDate.of(2008, Month.MAY, 8));
//
//        this.codeComplete = new Book("Code Complete", "Steve McConnel",
//                LocalDate.of(2004, Month.JUNE, 9));
//
//        this.mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks",
//                LocalDate.of(1975, Month.JANUARY, 1));
//
//        this.cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.
//                of(2008, Month.AUGUST, 1));
//        this.effectiveJava = new Book("Effective Java", "Joshua Bloch",
//                LocalDate.of(2008, Month.MAY, 8));

        this.codeComplete = books.get("Code Complete");
        this.mythicalManMonth = books.get("The Mythical Man-Month");
        this.cleanCode = books.get("Clean Code");
        this.effectiveJava = books.get("Effective Java");
        this.shelf = new BookShelf();
    }

    @Test
    @DisplayName("throws exception when books are added after capacity is reached primitive way")
    void throwsExceptionPrimitiveWay(){
        shelf.setBookShelfCapacity(2);
        try {
            shelf.add(effectiveJava, cleanCode);

            shelf.add(mythicalManMonth);
            fail("should bi thrown exception");

        }catch (BookShelfCapacityReachedException e){
            assertThat("BookShelf capacity " +
                    "of 2 is reached. You can't add more books.").
                    isEqualTo(e.getMessage());
        }
    }

    @Test

    @DisplayName("throws exception when books are added after capacity is reached JUnit5 way")
    void throwsExceptionJUnit5Way(){
        shelf.setBookShelfCapacity(2);
        BookShelfCapacityReachedException exception = assertThrows(BookShelfCapacityReachedException.class, () -> {
           shelf.add(mythicalManMonth, effectiveJava, cleanCode);
        });
        assertThat("BookShelf capacity " +
                "of 2 is reached. You can't add more books.").
                isEqualTo(exception.getMessage());
    }

}