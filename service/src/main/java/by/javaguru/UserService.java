package by.javaguru;

import by.javaguru.dao.UserDao;
import by.javaguru.entity.User;
import by.javaguru.exceptions.JsonReadWriteException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private final UserDao userDao = new UserDao();

    public UserService() throws JsonReadWriteException {
    }

    public Optional<UserDto> getUser(Long id) throws JsonReadWriteException {
        return userDao.findById(id).map(UserService::convertToDto);
    }

    public List<UserDto> getAllUsers() throws JsonReadWriteException {
        return userDao.findAll().stream().map(UserService::convertToDto).collect(Collectors.toList());
    }

    public void updateUser(UserDto user) throws JsonReadWriteException {
        userDao.update(convertFromDto(user));
    }

    private static UserDto convertToDto(User user) {
        return new UserDto(Long.toString(user.getId()), user.getSurname(), user.getName(), user.getPatronymic());
    }

    private static User convertFromDto(UserDto user) {
        return new User(Long.parseLong(user.getId()), user.getSurname(), user.getName(), user.getPatronymic());
    }
}
