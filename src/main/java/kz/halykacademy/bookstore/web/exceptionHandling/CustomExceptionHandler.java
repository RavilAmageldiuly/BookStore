package kz.halykacademy.bookstore.web.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(value = UserForbiddenException.class)
    protected ResponseEntity<Object> handleBlockedUserException(UserForbiddenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomError(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(value = UserBadRequestException.class)
    protected ResponseEntity<Object> handlePriceExceedsLimitException(UserBadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(exception.getMessage(), LocalDateTime.now()));
    }
}
