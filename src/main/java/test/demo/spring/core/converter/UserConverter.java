package test.demo.spring.core.converter;

import test.demo.spring.core.dto.UserDto;
import test.demo.spring.core.model.User;

public class UserConverter {

    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    public static User mapFromDto(UserDto user) {
        return User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }
}
