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

    @ExceptionHandler(value = BlockedUserException.class)
    protected ResponseEntity<Object> handleBlockedUserException(BlockedUserException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomError(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(value = PriceExceedsLimitException.class)
    protected ResponseEntity<Object> handlePriceExceedsLimitException(PriceExceedsLimitException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomError(exception.getMessage(), LocalDateTime.now()));
    }
}
