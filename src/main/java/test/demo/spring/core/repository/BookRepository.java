package test.demo.spring.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.demo.spring.core.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findBookByIsbn(String isbn);
    Optional<List<Book>> findAllByAuthorId(Long id);
}
