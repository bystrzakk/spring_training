package test.demo.spring.core.converter;

import test.demo.spring.core.dto.AuthorDto;
import test.demo.spring.core.model.Author;

public class AuthorConverter {

    private AuthorConverter() {
    }

    public static Author convertToAuthor(AuthorDto authorDto) {
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .nationality(authorDto.getNationality())
                .build();
    }

    public static AuthorDto convertToAuthorDto(Author author) {
        return AuthorDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .nationality(author.getNationality())
                .id(author.getId())
                .build();
    }
}
