package kz.halykacademy.bookstore.service.users;

import kz.halykacademy.bookstore.web.users.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUser(Long id);

    User postUser(User saveUser);

    User putUser(Long id, User saveUser);

    void deleteUser(Long id);
}
