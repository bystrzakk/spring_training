package test.demo.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.demo.spring.core.converter.AuthorConverter;
import test.demo.spring.core.dto.AuthorDto;
import test.demo.spring.core.model.Author;
import test.demo.spring.core.repository.AuthorRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto addNewAuthor(AuthorDto authorDto) {
        final Author author = AuthorConverter.convertToAuthor(authorDto);
        final Author saved = authorRepository.save(author);

        return AuthorConverter.convertToAuthorDto(saved);
    }

    public List<AuthorDto> getAllAuthors() {
        final List<Author> allAuthorsFromDb = authorRepository.findAll();

        return allAuthorsFromDb.stream().map(AuthorConverter::convertToAuthorDto).collect(toList());
    }

    public boolean deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
        return true;
    }

    public AuthorDto findAuthorByNameAndSurname(String firstName, String lastName) {
        final Author author = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        return AuthorConverter.convertToAuthorDto(author);
    }

}
