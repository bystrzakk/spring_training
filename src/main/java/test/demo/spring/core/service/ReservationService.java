package test.demo.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.demo.spring.core.dto.BookReservationDto;
import test.demo.spring.core.model.Book;
import test.demo.spring.core.model.BookReservation;
import test.demo.spring.core.model.Customer;
import test.demo.spring.core.repository.BookRepository;
import test.demo.spring.core.repository.CustomerRepository;
import test.demo.spring.core.repository.ReservationOrderRepository;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.Optional;

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

    public void createReservation(BookReservationDto bookReservationDto) {
        final Optional<Book> bookByIsbn = bookRepository.findBookByIsbn(bookReservationDto.getBookIsbn());
        final Optional<Customer> customer = customerRepository.findByFirstNameAndLastName(bookReservationDto.getFirstName(), bookReservationDto.getLastName());
        final Book savedBook;
        final Customer savedCustomer;

        if (!bookByIsbn.isPresent()) {
            throw new NoResultException("Cant find given book!");
        } else if (!customer.isPresent()) {
            final Customer newCustomer = Customer.builder()
                    .firstName(bookReservationDto.getFirstName())
                    .lastName(bookReservationDto.getLastName())
                    .phone(bookReservationDto.getPhone())
                    .email(bookReservationDto.getEmail()).build();
            final Book book = bookByIsbn.get();
            savedCustomer = customerRepository.save(newCustomer);
            book.setCustomer(savedCustomer);
            book.setAvailable(false);
            savedBook = bookRepository.save(book);
        } else {
            final Book book = bookByIsbn.get();
            book.setAvailable(false);
            savedCustomer = customer.get();
            book.setCustomer(savedCustomer);
            savedBook = bookRepository.save(book);
        }

        final BookReservation bookReservation = BookReservation.builder()
                .book(savedBook)
                .customer(savedCustomer)
                .reservationFrom(LocalDate.now())
                .reservationTo(LocalDate.now().plusMonths(1))
                .build();


        final BookReservation reservation = reservationOrderRepository.save(bookReservation);
        savedBook.setBookReservation(reservation);
        bookRepository.save(savedBook);
    }


}
