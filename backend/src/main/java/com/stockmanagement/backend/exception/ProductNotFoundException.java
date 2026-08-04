package com.stockmanagement.backend.exception;

/**
 * Exception thrown when a Product with the given identifier cannot be found.
 * This is a domain‑specific version of a 404 Not Found error.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product not found with id " + id);
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}