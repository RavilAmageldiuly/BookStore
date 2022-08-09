package kz.halykacademy.bookstore.security;

import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.security.jwt.JwtUserFactory;
import kz.halykacademy.bookstore.service.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userService;

    @Autowired
    public JwtUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User with username: " + username + " not found!");

        return JwtUserFactory.create(user);
    }
}
