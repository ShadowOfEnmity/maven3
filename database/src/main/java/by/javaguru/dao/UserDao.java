package by.javaguru.dao;

import by.javaguru.entity.User;
import by.javaguru.exceptions.JsonReadWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class UserDao {

    private List<User> users = new ArrayList<>();
    private ObjectMapper mapper;
    private static File file;


    public UserDao() throws JsonReadWriteException {

        mapper = new ObjectMapper();
        String filePath = System.getProperty("users.path");
        file = new File(filePath);
        if (file.exists()) {
            users = new ArrayList<>();
            users.add(new User(1, "Popov", "Ivan", "Aleksandrovich"));
            users.add(new User(2, "Kolenkina", "Svetlana", "Yuryevna"));
            users.add(new User(3, "Yakubovich", "Leonid", "Arkadevich"));
            users.add(new User(4, "Bocharov", "Andrey", "Nikolaevich"));
            users.add(new User(5, "Klishas", "Dmitry", "Andreevich"));
            users.add(new User(6, "Ayupova", "Yulia", "Nikolaevna"));
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
            } catch (IOException e) {
                throw new JsonReadWriteException("Can't write to the json database", e);
            }
        }

    }

    private List<User> readListFromJson() throws JsonReadWriteException {
        try {
            return mapper.readValue(file, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            throw new JsonReadWriteException("Can't read from the json database", e);
        }
    }

    public Optional<User> findById(Long id) throws JsonReadWriteException {
        users.clear();
        users.addAll(readListFromJson());
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public List<User> findAll() throws JsonReadWriteException {
        users.clear();
        users.addAll(readListFromJson());
        return Collections.unmodifiableList(users);
    }

    public void update(User user) throws JsonReadWriteException {
        users.clear();
        users.addAll(readListFromJson());
        Set<User> usersSet = new HashSet<>(users);

        users.stream().filter(oldUser -> oldUser.getId() == user.getId()).map(oldUser -> {
            oldUser.setSurname(user.getSurname());
            oldUser.setName(user.getName());
            oldUser.setPatronymic(user.getPatronymic());
            return oldUser;
        }).findFirst().ifPresent(usersSet::add);

        users.clear();
        users.addAll(usersSet);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (IOException e) {
            throw new JsonReadWriteException("Can't write to the json database", e);
        }


    }

}
