package kz.halykacademy.bookstore.web.users;


import kz.halykacademy.bookstore.dao.users.UserEntity;
import kz.halykacademy.bookstore.security.jwt.JwtTokenProvider;
import kz.halykacademy.bookstore.service.users.UserService;
import kz.halykacademy.bookstore.service.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestControllerV1 {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserServiceImpl userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {

        try {
            String username  = requestDto.getUsername();

//            System.out.println(username + " ; " + requestDto.getPassword());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

            UserEntity user = userService.findByUsername(username);

            if (user == null )
                throw new UsernameNotFoundException("User with username: " + username + " not found!");

//            System.out.println(username + " : " + user.getUserRole());

            String token = jwtTokenProvider.createToken(username, user.getUserRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch(AuthenticationException e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid username or password!");
        }
    }

}
