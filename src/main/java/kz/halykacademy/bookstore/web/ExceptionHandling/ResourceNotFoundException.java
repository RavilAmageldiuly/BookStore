package kz.halykacademy.bookstore.web.ExceptionHandling;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
