package kz.halykacademy.bookstore.web.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String userLogin;
    private String userPassword;
    private String userRole;
    private Boolean blockFlag;
}
