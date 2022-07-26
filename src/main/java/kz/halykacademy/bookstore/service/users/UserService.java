package kz.halykacademy.bookstore.service.users;

import kz.halykacademy.bookstore.web.users.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUser(Long id);

    User postUser(User saveUser);

    User changePassword(String username, String userPassword);

    User changeUsername(String oldUsername, String newUsername);

    User changeUser(Long userId, User saveUser);

    void deleteUser(String username);
}
