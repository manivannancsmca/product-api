package com.example.productmanagement.exception;

/**
 * Exception thrown when a resource with duplicate unique identifier already exists.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}