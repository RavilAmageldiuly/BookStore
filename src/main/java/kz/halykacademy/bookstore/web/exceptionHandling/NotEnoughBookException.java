package kz.halykacademy.bookstore.web.exceptionHandling;

public class NotEnoughBookException extends RuntimeException {
    public NotEnoughBookException(String message) {
        super(message);
    }
}
