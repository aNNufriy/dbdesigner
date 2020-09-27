package ru.testfield.web.exception;

/**
 * Created by J.Bgood on 8/16/17.
 */
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
