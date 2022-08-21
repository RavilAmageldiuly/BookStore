package kz.halykacademy.bookstore.web.exceptionHandling;

public class UserBadRequestException extends RuntimeException{
    public UserBadRequestException(String message) {
        super(message);
    }
}
