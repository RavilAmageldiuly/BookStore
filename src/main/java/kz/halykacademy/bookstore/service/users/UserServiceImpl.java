package kz.halykacademy.bookstore.service.users;

import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.dao.users.UserRepository;
import kz.halykacademy.bookstore.web.exceptionHandling.ResourceNotFoundException;
import kz.halykacademy.bookstore.web.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
    public User putUser(Long id, User saveUser) {

        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("User not found! Invalid id supplied.");

        saveUser.setUserPassword(passwordEncoder.encode(saveUser.getUserPassword()));


        return userRepository.save(
                new UserEntity(
                        id,
                        saveUser.getUserLogin(),
                        saveUser.getUserPassword(),
                        saveUser.getUserRole().toUpperCase(),
                        saveUser.getBlockFlag()
                )
        ).toDto();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("User not found! Invalid id supplied.");

        userRepository.deleteById(id);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }
}
