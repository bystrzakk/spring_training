package test.demo.spring.core.service;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.demo.spring.core.converter.BookReservationConverter;
import test.demo.spring.core.dto.BookReservationDto;
import test.demo.spring.core.model.Book;
import test.demo.spring.core.model.BookReservation;
import test.demo.spring.core.model.Customer;
import test.demo.spring.core.repository.BookRepository;
import test.demo.spring.core.repository.CustomerRepository;
import test.demo.spring.core.repository.ReservationOrderRepository;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final ReservationOrderRepository reservationOrderRepository;

    @Autowired
    public ReservationService(BookRepository bookRepository,
                              ReservationOrderRepository reservationOrderRepository,
                              CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.reservationOrderRepository = reservationOrderRepository;
    }

    public List<BookReservationDto> findAllReservations() {
        return reservationOrderRepository.findAll().stream().map(BookReservationConverter::convertToBookReservationDto).collect(Collectors.toList());
    }

    public void createReservation(BookReservationDto bookReservationDto) {
        final Optional<Book> bookByIsbn = bookRepository.findBookByIsbn(bookReservationDto.getBookIsbn());
        final Optional<Customer> customer = customerRepository.findByFirstNameAndLastName(bookReservationDto.getFirstName(), bookReservationDto.getLastName());
        final Book savedBook;
        final Customer savedCustomer;

        if (!bookByIsbn.isPresent()) {
            throw new NoResultException("Cant find given book!");
        } else if (!customer.isPresent()) {
            final Customer newCustomer = prepareNewCustomer(bookReservationDto);
            final Book book = bookByIsbn.get();
            checkIfBookIsTaken(book);
            savedCustomer = customerRepository.save(newCustomer);
            savedBook = oderBook(book, savedCustomer);
        } else {
            final Book book = bookByIsbn.get();
            savedCustomer = customer.get();
            checkIfBookIsTaken(book);
            savedBook = oderBook(book, savedCustomer);
        }

        final BookReservation reservation = reservationOrderRepository.save(prepareReservation(savedBook, savedCustomer));
        savedBook.setBookReservation(reservation);
        bookRepository.save(savedBook);
    }

    public void removeReservation(String isbn) {
        final Book bookByIsbn = bookRepository.findBookByIsbn(isbn).get();
        bookByIsbn.setCustomer(null);
        reservationOrderRepository.deleteById(bookByIsbn.getBookReservation().getId());
        bookByIsbn.setBookReservation(null);
        bookByIsbn.setAvailable(true);
        bookRepository.save(bookByIsbn);
    }

    private BookReservation prepareReservation(Book savedBook, Customer savedCustomer) {
        return BookReservation.builder()
                .book(savedBook)
                .customer(savedCustomer)
                .reservationFrom(LocalDate.now())
                .reservationTo(LocalDate.now().plusMonths(1))
                .build();
    }

    private void checkIfBookIsTaken(Book book) {
        if (book.getCustomer() != null || !book.isAvailable()) {
            throw new InternalException("Book is already taken by customer: " + book.getCustomer().toString());
        }
    }

    private Customer prepareNewCustomer(BookReservationDto bookReservationDto) {
        return Customer.builder()
                .firstName(bookReservationDto.getFirstName())
                .lastName(bookReservationDto.getLastName())
                .phone(bookReservationDto.getPhone())
                .email(bookReservationDto.getEmail()).build();
    }

    private Book oderBook(Book book, Customer customer) {
        book.setCustomer(customer);
        book.setAvailable(false);
        return bookRepository.save(book);
    }


}
