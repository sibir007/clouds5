package io.github.sibir007.clouds5.testjunit.bookstoread;

import java.util.ArrayList;
import java.util.List;

public class CompositeFilter implements BookFilter{
    private List<BookFilter> filters = new ArrayList<>();
    public void addFilter(BookFilter filter){
        filters.add(filter);
    }
    @Override
    public boolean apply(Book book) {
        return filters.
                stream().
//                map(filter -> filter.apply(book)).
//                reduce(true, (b1, b2) -> b1 && b2);
                reduce(true, (b1, b2) -> b1 && b2.apply(book), (b1, b2) -> b1 && b2);
    }
}
