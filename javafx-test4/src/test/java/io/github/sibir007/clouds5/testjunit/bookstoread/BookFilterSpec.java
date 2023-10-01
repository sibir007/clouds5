package io.github.sibir007.clouds5.testjunit.bookstoread;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
@Tag("Filter1")
@DisplayName("A book filter spec")
@ExtendWith(BooksParameterResolver.class)
public class BookFilterSpec implements FilterBoundaryTests{
    private Book cleanCode;
    private Book codeComplete;
    private BookFilter filter;

    @BeforeEach
    void init(Map<String, Book> books) {
        this.cleanCode = books.get("Clean Code");
        this.codeComplete = books.get("Code Complete");
        this.filter = BookPublishedYearFilter.Before(2007);
    }

    @Override
    public BookFilter get(){
        return filter;
    }

    @Nested
    @DisplayName("book published date")
    class BookPublishedFilterSpec {
        @Test
        @DisplayName("is after specified year")
        void validateBookPublishedDatePostAskedYear() {
            BookFilter filter = BookPublishedYearFilter.After(2007);

            assertAll(() -> assertTrue(filter.apply(cleanCode), () -> "должен быть после 2007"),
                    () -> assertFalse(filter.apply(codeComplete), () -> "должен быть до 2007"));

        }

        @Test
        @DisplayName("Composite criteria is based on multiple filters")
        void shouldFilterOnMultiplesCriteria() {
            CompositeFilter compositeFilter = new CompositeFilter();
            compositeFilter.addFilter(b -> false);
            assertFalse(compositeFilter.apply(cleanCode));
        }

        @Test
        @DisplayName("Composite фильтр должен останаливать проверку при дослижении певого false (false 1)")
        void compositeFilterMustStopAtFirstFalse() {
            AtomicInteger countCall = new AtomicInteger();
            CompositeFilter compositeFilter = new CompositeFilter();
            compositeFilter.addFilter(b -> {
                countCall.set(countCall.get() + 1);
                return false;
            });
            compositeFilter.addFilter(book -> {
                countCall.set(countCall.get() + 1);
                return false;
            });
            compositeFilter.addFilter(book -> {
                countCall.set(countCall.get() + 1);
                return true;
            });
            compositeFilter.apply(cleanCode);
            assertEquals(1, countCall.get(), () -> "compositeFilter должен вызваться 1 раз");
        }

        @Test
        @DisplayName("Composite фильтр должен останаливать проверку при дослижении певого false  (false 3)")
        void compositeFilterMustStopAtFirstFalse2() {
            AtomicInteger countCall = new AtomicInteger();
            CompositeFilter compositeFilter = new CompositeFilter();
            compositeFilter.addFilter(b -> {
                countCall.set(countCall.get() + 1);
                return true;
            });
            compositeFilter.addFilter(book -> {
                countCall.set(countCall.get() + 1);
                return true;
            });
            compositeFilter.addFilter(book -> {
                countCall.set(countCall.get() + 1);
                return false;
            });

            compositeFilter.addFilter(book -> {
                countCall.set(countCall.get() + 1);
                return true;
            });
            compositeFilter.apply(cleanCode);
            assertEquals(3, countCall.get(), () -> "compositeFilter должен вызваться 3 раза");
        }
    }

    @Nested
    @DisplayName("mocking реализация")
    class MockingBookPublishedFilterSpec {
        @Test
        @DisplayName("Composite criteria does not invoke after first failure")
        void shouldNotInvokeAfterFirstFailure() {
            CompositeFilter compositeFilter = new CompositeFilter();
            BookFilter invokedMockedFilter = Mockito.mock(BookFilter.class);
            Mockito.when(invokedMockedFilter.apply(cleanCode)).thenReturn(false);
            compositeFilter.addFilter(invokedMockedFilter);

            BookFilter nonInvokedMockedFilter = Mockito.mock(BookFilter.class);
            Mockito.when(nonInvokedMockedFilter.apply(cleanCode)).thenReturn(true);
            compositeFilter.addFilter(nonInvokedMockedFilter);

            assertFalse(compositeFilter.apply(cleanCode), () -> "фильте должне вернуть false");
            Mockito.verify(invokedMockedFilter).apply(cleanCode);
            Mockito.verifyNoInteractions(nonInvokedMockedFilter);

        }

        @Test
        @DisplayName("Composite criteria invokes all filters")
        void shouldInvokeAllFilters(){
            CompositeFilter compositeFilter = new CompositeFilter();
            BookFilter firstInvokedMockedFilter = Mockito.mock(BookFilter.class);
            Mockito.when(firstInvokedMockedFilter.apply(cleanCode)).thenReturn(true);
            compositeFilter.addFilter(firstInvokedMockedFilter);

            BookFilter secondInvokedMockedFilter = Mockito.mock(BookFilter.class);
            Mockito.when(secondInvokedMockedFilter.apply(cleanCode)).thenReturn(true);
            compositeFilter.addFilter(secondInvokedMockedFilter);

            assertTrue(compositeFilter.apply(cleanCode), () -> "фильте должне вернуть true");
            Mockito.verify(firstInvokedMockedFilter).apply(cleanCode);
            Mockito.verify(secondInvokedMockedFilter).apply(cleanCode);
        }
    }


}
