package test.demo.spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.demo.spring.core.dto.BookDto;
import test.demo.spring.core.service.BookService;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto bookDto) {
        final BookDto newBook = bookService.addNewBook(bookDto);

        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        final List<BookDto> allBooks = bookService.getAllBooks();

        return new ResponseEntity<>(allBooks, OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable("isbn") String bookIsbn) {
        final BookDto bookByIsbn = bookService.findBookByIsbn(bookIsbn);

        return new ResponseEntity<>(bookByIsbn, OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDto>> getBookByAuthorId(@PathVariable("authorId") Long authorId) {
        final List<BookDto> bookByIsbn = bookService.findBooksByAuthorId(authorId);

        return new ResponseEntity<>(bookByIsbn, OK);
    }

    @PutMapping("/{isbn}/{authorId}")
    public ResponseEntity<Object> assignBookToAuthor(@PathVariable("isbn") String bookIsbn, @PathVariable("authorId") Long authorId) {
        bookService.assignAuthorToBook(bookIsbn, authorId);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable("isbn") String bookIsbn) {
        return new ResponseEntity<>(bookService.deleteBook(bookIsbn), ACCEPTED);
    }
}
