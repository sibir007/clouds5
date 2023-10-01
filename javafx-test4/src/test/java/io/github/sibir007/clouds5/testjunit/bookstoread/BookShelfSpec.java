package io.github.sibir007.clouds5.testjunit.bookstoread;

import io.github.sibir007.clouds5.testjunit.bookstoread.BookShelf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfSpec {
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
    }

    @Nested
    @DisplayName("is empty")
    class IsEmpty {
        @Test
        @DisplayName("when no book is added to it")
        public void shelfEmptyWhenNoBookAdded() {
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
        }

        @Test
        @DisplayName("when add is called without books")
        public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
            shelf.add();
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
        }
    }


    @Nested
    @DisplayName("after adding books")
    class BooksAreAdded {
        @Test
        @DisplayName("contain two books")
        public void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
            shelf.add(effectiveJava, codeComplete);
            List<Book> books = shelf.books();

            assertEquals(2, books.size(), () -> "BookShelf should have two books.");
        }

        @Test
        @DisplayName("returns an immutable books collection to client")
        public void booksReturnedFromBookShelfIsImmutableForClient() {
            shelf.add(effectiveJava, codeComplete);
            List<Book> books = shelf.books();
            try {
                books.add(mythicalManMonth);
                fail(() -> "Should not be able to add book to books");
            } catch (Exception e) {
                assertTrue(e instanceof UnsupportedOperationException, () ->
                        "Should throw UnsupportedOperationException.");
            }
        }
    }

    @Nested
    @DisplayName("is arranged")
    class BooksArranged {
        @Disabled("Need to implement Comparator")
        @Test
        @DisplayName("bookshelf is arranged lexicographically by book title")
        void bookshelfArrangedByBookTitle() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth);
            List<Book> books = shelf.arrange();
            assertEquals(Arrays.asList(codeComplete, effectiveJava,
                            mythicalManMonth), books,
                    () -> "Books in a bookshelf should be arranged lexicographically by book title");
        }

        @Test
        void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth);
            shelf.arrange();
            List<Book> books = shelf.books();

            assertEquals(Arrays.asList(effectiveJava, codeComplete, mythicalManMonth),
                    books, () -> "Books in bookshelf are in insertion order");
        }

        @Test
        void bookshelfArrangedByUserProvidedCriteria() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth);
            Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
            List<Book> books = shelf.arrange(reversed);
            assertThat(books).isSortedAccordingTo(reversed);
        }

    }


//    @Disabled("Needs to implement Comparator")
//    @Test
//    void bookshelfArrangedByUserProvidedCriteria() {
//        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
//        List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
//        assertEquals(
//                Arrays.asList(mythicalManMonth, effectiveJava, codeComplete),
//                books,
//                () -> "Books in a bookshelf are arranged in order of book title"
//        );
//    }

    @Nested
    @DisplayName("grouping")
    class Grouping {
        @Test
        @DisplayName("books inside bookshelf are grouped by publication year")
        void groupBooksInsideBookShelfByPublicationYear() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
            Map<Year, List<Book>> booksByPublicationYear = shelf.
                    groupByPublicationYear();
            assertThat(booksByPublicationYear)
                    .containsKey(Year.of(2008))
                    .containsValues(Arrays.asList(effectiveJava, cleanCode));
            assertThat(booksByPublicationYear)
                    .containsKey(Year.of(2004))
                    .containsValues(Collections.singletonList(codeComplete));
            assertThat(booksByPublicationYear)
                    .containsKey(Year.of(1975))
                    .containsValues(Collections.singletonList(mythicalManMonth));
        }

        @Test
        @DisplayName("books inside bookshelf are grouped according to user provided criteria(group by author name)")
        void groupBooksByUserProvidedCriteria() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
            Map<String, List<Book>> booksByAutor = shelf.groupBy(Book::getAuthor);
        }
    }

    @Nested
    @DisplayName("search")
    class BookShelfSearchSpec {
        @BeforeEach
        void setup() {
            shelf.add(codeComplete, effectiveJava, mythicalManMonth, cleanCode);
        }

        @Test
        @DisplayName("should find books with title containing text")
        void shouldFindBooksWithTitleContainingText() {
            List<Book> books = shelf.findBooksByTitle("code");
            assertThat(books).isEqualTo(List.of(codeComplete, cleanCode));
        }

        @Test
        @DisplayName("should find books with title containing text and published after specified date")
        void shouldFilterSearchedBooksBasedOnPublishedDate() {
            List<Book> books = shelf.findBooksByTitle("code", book -> book.getPublishedOn().isAfter(LocalDate.of(2005,12,31)));
            assertThat(books).isEqualTo(List.of(cleanCode));
        }

    }

}