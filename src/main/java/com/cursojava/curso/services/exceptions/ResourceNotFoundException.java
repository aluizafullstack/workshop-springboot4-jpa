package com.cursojava.curso.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final long serialVersionUID = 1L;

    public ResourceNotFoundException (Object id) {
        super("Resource not found. Id: " + id);
    }
}
