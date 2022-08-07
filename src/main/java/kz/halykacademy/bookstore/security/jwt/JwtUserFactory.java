package kz.halykacademy.bookstore.security.jwt;


import kz.halykacademy.bookstore.dao.users.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getUserId(),
                user.getUsername(),
                user.getUserPassword(),
                !user.getBlockFlag(),
                List.of(new SimpleGrantedAuthority(user.getUserRole()))
        );
    }

}
