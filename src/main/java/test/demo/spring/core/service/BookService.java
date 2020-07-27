package test.demo.spring.core.service;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.demo.spring.core.converter.BookConverter;
import test.demo.spring.core.dto.BookDto;
import test.demo.spring.core.model.Author;
import test.demo.spring.core.model.Book;
import test.demo.spring.core.repository.AuthorRepository;
import test.demo.spring.core.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookDto addNewBook(BookDto bookDto) {
        final Book book = BookConverter.convertToBook(bookDto);
        bookRepository.save(book);

        return bookDto;
    }

    public List<BookDto> getAllBooks() {
        final List<Book> allBooksFromDb = bookRepository.findAll();

        return allBooksFromDb.stream().map(BookConverter::convertToBookDto).collect(toList());
    }

    public BookDto findBookByIsbn(String bookIsbn) {
        return bookRepository.findBookByIsbn(bookIsbn)
                .map(BookConverter::convertToBookDto)
                .orElse(null);
    }

    public List<BookDto> findBooksByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthorId(authorId)
                .map(books -> books.stream().map(BookConverter::convertToBookWithoutAuthorDto).collect(Collectors.toList()))
                .orElse(null);
    }

    public void assignAuthorToBook(String bookIsbn, Long authorId) {
        final Optional<Book> bookByIsbn = bookRepository.findBookByIsbn(bookIsbn);
        final Optional<Author> author = authorRepository.findById(authorId);
        if (bookByIsbn.isPresent() && author.isPresent()) {
            final Book book = bookByIsbn.get();
            book.setAuthor(author.get());
            bookRepository.save(book);
        } else {
            throw new InternalException("It was impossible to assing author to given book!");
        }
    }

    public boolean deleteBook(String bookIsbn) {
        bookRepository.deleteById(bookIsbn);
        return true;
    }


}
