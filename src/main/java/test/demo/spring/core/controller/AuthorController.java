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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.demo.spring.core.dto.AuthorDto;
import test.demo.spring.core.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addNewAuthor(@RequestBody AuthorDto authorDto) {
        final AuthorDto newAuthor = authorService.addNewAuthor(authorDto);

        return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors(@RequestParam(name = "firstName", required = false) String firstName,
                                                      @RequestParam(name = "lastName", required = false) String lastName) {
        final List<AuthorDto> allAuthors = new ArrayList<>();
        if (isEmpty(firstName) && isEmpty(lastName)) {
            allAuthors.addAll(authorService.getAllAuthors());
        } else {
            allAuthors.add(authorService.findAuthorByNameAndSurname(firstName, lastName));
        }

        return new ResponseEntity<>(allAuthors, OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Boolean> deleteAuthor(@PathVariable(name = "authorId") Long authorId) {
        return new ResponseEntity<>(authorService.deleteAuthor(authorId), ACCEPTED);
    }

}
