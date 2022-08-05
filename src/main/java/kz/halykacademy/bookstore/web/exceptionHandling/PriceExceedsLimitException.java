package kz.halykacademy.bookstore.web.exceptionHandling;

public class PriceExceedsLimitException extends RuntimeException{
    public PriceExceedsLimitException(String message) {
        super(message);
    }
}
