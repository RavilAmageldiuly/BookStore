package kz.halykacademy.bookstore.service.users;

import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.dao.users.UserRepository;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.exceptionHandling.UserBadRequestException;
import kz.halykacademy.bookstore.web.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        if (userRepository.findUserEntityByUsername(saveUser.getUsername()) != null)
            throw new UserBadRequestException("User with username: " + saveUser.getUsername() + " already exists!");

        saveUser.setUserPassword(passwordEncoder.encode(saveUser.getUserPassword()));

        return userRepository.save(
                new UserEntity(
                        null,
                        saveUser.getUsername(),
                        saveUser.getUserPassword(),
                        "USER",
                        false
                )
        ).toDto();
    }


    @Transactional
    @Override
    public User changePassword(String username, String password) {
        userRepository.updateUserPassword(username, passwordEncoder.encode(password));

        return userRepository.findUserEntityByUsername(username).toDto();
    }


    @Transactional
    @Override
    public User changeUsername(String oldUsername, String newUsername) {
        userRepository.updateUsername(oldUsername, newUsername);

        return userRepository.findUserEntityByUsername(newUsername).toDto();
    }

    @Override
    public User changeUser(Long userId, User saveUser) {

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found! Invalid id supplied."));

        return userRepository.save(
                new UserEntity(
                        userId,
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
