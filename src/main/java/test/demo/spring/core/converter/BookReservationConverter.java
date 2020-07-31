package test.demo.spring.core.converter;

import test.demo.spring.core.dto.BookReservationDto;
import test.demo.spring.core.model.BookReservation;
import test.demo.spring.core.model.Customer;

public class BookReservationConverter {

    private BookReservationConverter() {
    }

    public static BookReservationDto convertToBookReservationDto(BookReservation reservation) {
        if (reservation != null) {
            final Customer customer = reservation.getCustomer();
            return BookReservationDto.builder()
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .reservedTo(reservation.getReservationTo())
                    .reservedFrom(reservation.getReservationFrom())
                    .email(customer.getEmail())
                    .phone(customer.getPhone())
                    .build();
        }
        return null;
    }

}
