package test.demo.spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.demo.spring.core.dto.BookReservationDto;
import test.demo.spring.core.service.ReservationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Object> addNewReservation(@Valid @RequestBody BookReservationDto bookReservationDto) {
        reservationService.createReservation(bookReservationDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookReservationDto>> getAllReservations() {
        final List<BookReservationDto> allReservations = reservationService.findAllReservations();

        return new ResponseEntity<>(allReservations, HttpStatus.OK);
    }

    @DeleteMapping("/{bookIsbn}")
    public ResponseEntity<Boolean> removeReservation(@PathVariable(name = "bookIsbn") String bookIsbn) {
        reservationService.removeReservation(bookIsbn);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
