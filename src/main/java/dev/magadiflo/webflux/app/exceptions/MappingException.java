package dev.magadiflo.webflux.app.exceptions;

public class MappingException extends RuntimeException {
    public MappingException(String message) {
        super(message);
    }
}
