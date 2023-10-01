package io.github.sibir007.clouds5.testjunit.bookstoread.extension.parameterised;

import io.github.sibir007.clouds5.testjunit.bookstoread.Book;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookPublishedYearFilter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

public class BeforeYearArgsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Book cleanCode = new Book("Clean Code", "Robert C. Martin",
                LocalDate.of(2006, Month.AUGUST, 1));

        Book codeComplete = new Book("Code Complete", "Steve McConnel",
                LocalDate.of(2004, Month.JUNE, 9));

        return Stream.of(Arguments.of(BookPublishedYearFilter.Before(2007),
                        cleanCode),

                Arguments.of(BookPublishedYearFilter.Before(2007),
                        codeComplete));
    }
}
