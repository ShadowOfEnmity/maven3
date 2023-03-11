package by.itacademy;

import by.itacademy.dao.UserDao;

import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDao();

    public Optional<UserDto> getUser(Long id) {
        return userDao.findbyId(id).map(it -> new UserDto(it.getName()));
    }
}
