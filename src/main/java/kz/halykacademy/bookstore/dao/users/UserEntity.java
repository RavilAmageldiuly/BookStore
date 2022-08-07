package kz.halykacademy.bookstore.dao.users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dao.orders.OrderEntity;
import kz.halykacademy.bookstore.web.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "username")
    private String username;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "block_flag")
    private Boolean blockFlag;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private List<OrderEntity> orders = new ArrayList<>();

    public User toDto() {
        return new User(
                this.userId,
                this.username,
                this.userPassword,
                this.userRole,
                this.blockFlag
        );
    }
}
