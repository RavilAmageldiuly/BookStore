package kz.halykacademy.bookstore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.lang.System.out;

@SpringBootApplication
public class Main
{
    public static void main( String[] args ) {
        SpringApplication.run(Main.class, args);
        out.print("Hello world from first Spring boot app");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
