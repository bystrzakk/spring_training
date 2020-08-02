package test.demo.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.demo.spring.core.converter.UserConverter;
import test.demo.spring.core.dto.UserDto;
import test.demo.spring.core.model.User;
import test.demo.spring.core.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        final List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(UserConverter::mapToDto)
                .collect(Collectors.toList());
    }

    public void addNewUser(UserDto newUser) {
        final User user = UserConverter.mapFromDto(newUser);
        userRepository.save(user);
    }
}
