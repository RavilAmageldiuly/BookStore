package kz.halykacademy.bookstore.service.users;

import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.dao.users.UserRepository;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAll() {
        return userRepository.findAll().stream().map(UserEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).map(UserEntity::toDto).orElseThrow(() -> new ResourceNotFoundException("User not found! Invalid id supplied"));
    }

    @Override
    public User postUser(User saveUser) {

        saveUser.setUserPassword(passwordEncoder.encode(saveUser.getUserPassword()));

        return userRepository.save(
                new UserEntity(
                        saveUser.getUserId(),
                        saveUser.getUserLogin(),
                        saveUser.getUserPassword(),
                        "USER",
                        false
                )
        ).toDto();
    }


    @Override
    public User changePassword(String username, String password) {
        return userRepository.updateUserPassword(username, password).toDto();
    }

    @Override
    public User changeUsername(String oldUsername, String newUsername) {
        return userRepository.updateUsername(oldUsername, newUsername).toDto();
    }

    @Override
    public User changeUser(Long userId, User saveUser) {

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found! Invalid id supplied."));

        return userRepository.save(
                new UserEntity(
                        user.getUserId(),
                        user.getUsername(),
                        user.getUserPassword(),
                        saveUser.getUserRole().toUpperCase(),
                        saveUser.getBlockFlag()
                )
        ).toDto();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(userRepository.findUserEntityByUsername(username).getUserId());
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }
}
