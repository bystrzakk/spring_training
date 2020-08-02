package test.demo.spring.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.demo.spring.core.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
