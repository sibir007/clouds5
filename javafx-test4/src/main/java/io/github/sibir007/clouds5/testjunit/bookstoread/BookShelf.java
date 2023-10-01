package io.github.sibir007.clouds5.testjunit.bookstoread;

import io.github.sibir007.clouds5.testjunit.bookstoread.exceptions.BookShelfCapacityReachedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Year;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookShelf {
    private static final int DEFAULT_BOOKSHELF_CAPACITY = 100;
    private static Logger logger = LogManager.getLogger();
    private final List<Book> books = new ArrayList<>();
    private int bookShelfCapacity = DEFAULT_BOOKSHELF_CAPACITY;

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... booksToAdd) throws BookShelfCapacityReachedException {
            Arrays.stream(booksToAdd).forEach(
                    new Consumer<Book>() {
                        @Override
                        public void accept(Book book) {
                            if (BookShelf.this.books.size() == BookShelf.this.bookShelfCapacity) {
                                throw new BookShelfCapacityReachedException(String.format("BookShelf capacity " +
                                        "of %d is reached. You can't add more books.", BookShelf.this.bookShelfCapacity));
                            }
                            books.add(book);
                        }
                    });

        }



    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Map<Year, List<Book>> groupByPublicationYear() {
        return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
//        return books.stream().collect(Collectors.groupingBy(book -> Year.of(book.getPublishedOn().getYear())));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books.stream().collect(Collectors.groupingBy(fx));
    }

    public Progress progress() {
        long booksRead = books.stream().filter(Book::isRead).count();
        int percentageCompleted = (int) (((float) booksRead) / ((float) books.size()) * 100);
        int percentageToRead = (int) (((float) (books.size() - booksRead)) / ((float) books.size()) * 100);
        return new Progress(percentageCompleted, percentageToRead, 0);
    }

    public List<Book> findBooksByTitle(String text) {
        return findBooksByTitle(text, book -> true);
    }

    public List<Book> findBooksByTitle(String text, BookFilter filter) {
        return books.stream().filter(book -> book.getTitle().toLowerCase().
                        contains(text)).filter(book -> filter.apply(book)).
                collect(Collectors.toList());
    }

    public void setBookShelfCapacity(int bookShelfCapacity) {
        this.bookShelfCapacity = bookShelfCapacity;
    }

    public int getBookShelfCapacity() {
        return this.bookShelfCapacity;
    }
//    public
}