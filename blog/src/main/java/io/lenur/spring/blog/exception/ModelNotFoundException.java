package io.lenur.spring.blog.exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelNotFoundException(String message) {
        super(message);
    }
}
