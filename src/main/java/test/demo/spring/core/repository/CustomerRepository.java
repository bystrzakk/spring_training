package test.demo.spring.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.demo.spring.core.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //NATIVE: @Query(value = "SELECT c FROM Customer c WHERE c.first_name = ?1 and c.last_name = ?2", nativeQuery = true)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //JQPL: @Query("SELECT c FROM Customer c WHERE c.firstName = :firstName and c.lastName = :lastName")
    //Optional<Customer> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //QUERY Methods
    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
