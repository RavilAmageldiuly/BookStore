package kz.halykacademy.bookstore.web.exceptionHandling;

public class BlockedUserException extends RuntimeException{
    public BlockedUserException(String message) {
        super(message);
    }
}
