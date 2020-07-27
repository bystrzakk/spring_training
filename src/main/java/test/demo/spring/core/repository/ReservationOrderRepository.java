package test.demo.spring.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.demo.spring.core.model.BookReservation;

@Repository
public interface ReservationOrderRepository extends JpaRepository<BookReservation, Long> {
}
