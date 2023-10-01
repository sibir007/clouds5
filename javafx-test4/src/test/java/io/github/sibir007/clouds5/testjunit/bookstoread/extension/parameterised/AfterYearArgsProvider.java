package io.github.sibir007.clouds5.testjunit.bookstoread.extension.parameterised;

import io.github.sibir007.clouds5.testjunit.bookstoread.Book;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookPublishedYearFilter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

public class AfterYearArgsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Book cleanCode = new Book("Clean Code", "Robert C. Martin",
                LocalDate.of(2009, Month.AUGUST, 1));

        Book codeComplete = new Book("Code Complete", "Steve McConnel",
                LocalDate.of(2008, Month.JUNE, 9));

        return Stream.of(Arguments.of(BookPublishedYearFilter.After(2007),
                        cleanCode),

                Arguments.of(BookPublishedYearFilter.After(2007),
                        codeComplete));
    }
}
