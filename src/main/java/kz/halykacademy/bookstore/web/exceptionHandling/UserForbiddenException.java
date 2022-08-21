package kz.halykacademy.bookstore.web.exceptionHandling;

public class UserForbiddenException extends RuntimeException{
    public UserForbiddenException(String message) {
        super(message);
    }
}
