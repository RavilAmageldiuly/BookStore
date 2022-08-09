package kz.halykacademy.bookstore.web.exceptionHandling;

public class AttemptToUseAlienResource extends RuntimeException{
    public AttemptToUseAlienResource(String message) {
        super(message);
    }
}
