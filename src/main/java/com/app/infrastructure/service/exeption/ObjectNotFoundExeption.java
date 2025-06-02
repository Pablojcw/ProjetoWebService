package com.app.infrastructure.service.exeption;

public class ObjectNotFoundExeption extends  RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundExeption(String message) {
        super(message);
    }
}
