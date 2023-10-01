package io.github.sibir007.clouds5.testjunit.bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//@DisplayName("A bookshelf progress")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfProgressSpec {
    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;
    private Book refactoring;

    @BeforeEach
    void init(Map<String, Book> bookMap) {
        shelf = new BookShelf();

        this.effectiveJava = bookMap.get("Effective Java");
//                new Book("Effective Java", "Joshua Bloch",
//                LocalDate.of(2008, Month.MAY, 8));

        this.codeComplete = bookMap.get("Code Complete");
//        new Book("Code Complete", "Steve McConnel",
//                LocalDate.of(2004, Month.JUNE, 9));

        this.mythicalManMonth = bookMap.get("The Mythical Man-Month");
//        new Book("The Mythical Man-Month", "Frederick Phillips Brooks",
//                LocalDate.of(1975, Month.JANUARY, 1));

        this.cleanCode = bookMap.get("Clean Code");
//        new Book("Clean Code", "Robert C. Martin", LocalDate.
//                of(2008, Month.AUGUST, 1));
        this.effectiveJava = bookMap.get("Effective Java");
//        new Book("Effective Java", "Joshua Bloch",
//                LocalDate.of(2008, Month.MAY, 8));
        this.refactoring = bookMap.get("Refactoring: Improving the Design of Existing Code");
//        new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", LocalDate.of(2002, Month.MARCH, 9));

        shelf.add(effectiveJava, cleanCode, refactoring, mythicalManMonth, codeComplete);
    }

    @Test
    @DisplayName("is 0% completed and 100% to-read when no book is read yet")
    void progress100PercentUnread() {
        Progress progress = shelf.progress();
        assertThat(progress.completed()).isEqualTo(0);
        assertThat(progress.toRead()).isEqualTo(100);
    }

    @Tag("nightly")
    @Tag("generate-progress")
    @Test
    @DisplayName("is 40% completed and 60% to-read when 2 books are finished and 3books not read yet")
    void progressWithCompletedAndToReadPercentages() {
        effectiveJava.startedReadingOn(LocalDate.of(2016, Month.JULY, 1));
        effectiveJava.finishedReadingOn(LocalDate.of(2016, Month.JULY, 31));
        cleanCode.startedReadingOn(LocalDate.of(2016, Month.AUGUST, 1));
        cleanCode.finishedReadingOn(LocalDate.of(2016, Month.AUGUST, 31));
        Progress progress = shelf.progress();
        assertThat(progress.completed()).isEqualTo(40);
        assertThat(progress.toRead()).isEqualTo(60);
    }
}
