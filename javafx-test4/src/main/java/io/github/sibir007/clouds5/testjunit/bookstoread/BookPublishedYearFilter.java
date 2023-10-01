package io.github.sibir007.clouds5.testjunit.bookstoread;

import java.time.LocalDate;
import java.util.Objects;

abstract public class BookPublishedYearFilter implements BookFilter {


    public static BookFilter After(int year) {
        return new BookPublishedAfterYear(year);
    }


    public static BookFilter Before(int year) { return new BookPublishedBeforeYear(year);}

    @Override
    public boolean apply(Book book){
     if (Objects.isNull(book)) return false;
     return doApply(book);
    }

    abstract boolean doApply(Book book);
    private static class BookPublishedAfterYear extends BookPublishedYearFilter{
        private final LocalDate year;

        public BookPublishedAfterYear (int year){
            super();
            this.year = LocalDate.of(year, 12, 31);
        }
        @Override
        boolean doApply(Book book) {
            return book.getPublishedOn().isAfter(year);
        }

    }
    private static class BookPublishedBeforeYear extends BookPublishedYearFilter{
        private final LocalDate year;

        public BookPublishedBeforeYear (int year){
            super();
            this.year = LocalDate.of(year, 12, 31);
        }
        @Override
        boolean doApply(Book book) {
            return book.getPublishedOn().isBefore(year);
        }

    }

}
