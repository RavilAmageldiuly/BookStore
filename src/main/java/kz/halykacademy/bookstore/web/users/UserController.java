package kz.halykacademy.bookstore.web.users;


import kz.halykacademy.bookstore.service.users.UserServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.getAll();
    }

    @GetMapping()
    public User findOne(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUser(userService.findByUsername(userDetails.getUsername()).getUserId());
    }

    @GetMapping("/{id}")
    public User findOneById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User postUser(@RequestBody User saveUser) {
        return userService.postUser(saveUser);
    }

    @PutMapping("/putOwn/{id}")
    public User putOwnUser(@PathVariable Long id, @RequestBody User user) {
        return userService.putUser(id, user);
    }

    @PutMapping("/{id}")
    public User putUser(@PathVariable Long id, @RequestBody User user) {
        return userService.putUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
