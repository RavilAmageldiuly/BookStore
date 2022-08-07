package kz.halykacademy.bookstore.web.users;


import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
