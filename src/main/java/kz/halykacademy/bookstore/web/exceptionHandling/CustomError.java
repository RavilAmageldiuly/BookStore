package kz.halykacademy.bookstore.web.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CustomError {
    private String message;
    private LocalDateTime time;
}
