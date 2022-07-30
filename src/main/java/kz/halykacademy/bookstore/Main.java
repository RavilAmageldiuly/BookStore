package kz.halykacademy.bookstore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.out;

@SpringBootApplication
public class Main
{
    public static void main( String[] args ) {
        SpringApplication.run(Main.class, args);
        out.print("Hello world from first Spring boot app");
    }
}
