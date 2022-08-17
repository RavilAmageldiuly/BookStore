package kz.halykacademy.bookstore.web.exceptionHandling;

public class NotEnoughBooksException extends RuntimeException {
    public NotEnoughBooksException(String message) {
        super(message);
    }
}
