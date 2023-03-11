package by.itacademy.dao;

import by.itacademy.entity.User;

import java.util.Optional;

public class UserDao {
    public Optional<User> findbyId(Long id) {
        User user = new User();
        user.setName("Андрей");
        return Optional.of(user);
    }
}
