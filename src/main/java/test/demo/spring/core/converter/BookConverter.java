package test.demo.spring.core.converter;

import test.demo.spring.core.dto.AuthorDto;
import test.demo.spring.core.dto.BookDto;
import test.demo.spring.core.model.Book;

import static java.util.Objects.isNull;

public class BookConverter {

    private BookConverter() {
    }

    public static Book convertToBook(BookDto bookDto) {
        return Book.builder()
                .isbn(bookDto.getIsbn())
                .name(bookDto.getName())
                .pages(bookDto.getPages())
                .year(bookDto.getYear())
                .isAvailable(true)
                .build();
    }

    public static BookDto convertToBookDto(Book book) {
        final AuthorDto authorDto = isNull(book.getAuthor()) ? null : AuthorConverter.convertToAuthorDto(book.getAuthor());
        return new BookDto(book.getIsbn(), book.getName(), book.getPages(), book.getYear(), authorDto);
    }

    public static BookDto convertToBookWithoutAuthorDto(Book book) {
        return new BookDto(book.getIsbn(), book.getName(), book.getPages(), book.getYear(), null);
    }
}
