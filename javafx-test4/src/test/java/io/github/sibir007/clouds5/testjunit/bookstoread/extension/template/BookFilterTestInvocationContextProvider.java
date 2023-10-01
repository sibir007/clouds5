package io.github.sibir007.clouds5.testjunit.bookstoread.extension.template;

import io.github.sibir007.clouds5.testjunit.bookstoread.Book;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookFilter;
import io.github.sibir007.clouds5.testjunit.bookstoread.BookPublishedYearFilter;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.time.*;
import java.util.List;
import java.util.stream.Stream;

public class BookFilterTestInvocationContextProvider implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {

        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        Book cleanCode = new Book("Clean Code", "Robert C. Martin",
                LocalDate.of(2008, Month.AUGUST, 1));
        Book codeComplete = new Book("Code Complete", "Steve McConnel",
                LocalDate.of(2004, Month.JUNE, 9));
        return Stream.of(bookFilterTestContext("Before filter",
                BookPublishedYearFilter.Before(2007),
                        cleanCode, codeComplete),
                bookFilterTestContext("After filter",
                        BookPublishedYearFilter.After(2007),
                        codeComplete, cleanCode));
    }

    private TestTemplateInvocationContext bookFilterTestContext(String testName,
                                                                BookFilter bookFilter,
                                                                Book... array) {
        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return testName;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Lists.newArrayList(new TypedParameterResolver<BookFilter>(bookFilter),
                        new TypedParameterResolver<Book[]>(array));
            }
        };
    }
}
