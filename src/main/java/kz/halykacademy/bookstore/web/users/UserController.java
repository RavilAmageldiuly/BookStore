package kz.halykacademy.bookstore.web.users;


import kz.halykacademy.bookstore.service.users.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public Page<User> findAll(Pageable pageRequest) {
        return new PageImpl<>(
                userService.getAll()
                        .stream().skip(pageRequest.getOffset()).limit(pageRequest.getPageSize()).collect(Collectors.toList())
        );
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

    @PutMapping("/putOwn/updateUsername")
    public User changeUsername(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(value = "username") String username) {
        return userService.changeUsername(userDetails.getUsername(), username);
    }

    @PutMapping("/putOwn/updatePassword")
    public User changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(value = "password") String password) {
        return userService.changePassword(userDetails.getUsername(), password);
    }

    @PutMapping("/{id}")
    public User putUser(@PathVariable Long id, @RequestBody User user) {
        return userService.changeUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
