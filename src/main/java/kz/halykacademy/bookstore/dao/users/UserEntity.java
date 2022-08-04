package kz.halykacademy.bookstore.dao.users;


import kz.halykacademy.bookstore.web.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "block_flag")
    private Boolean blockFlag;

    public User toDto() {
        return new User(
                this.userId,
                this.userLogin,
                this.userPassword,
                this.userRole,
                this.blockFlag
        );
    }
}
