package com.stockmanagement.backend.exception;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Standard shape for every error response sent to clients.
 */
public record ApiError(
        LocalDateTime timestamp,   // when the error happened
        int status,                // HTTP status code
        String error,              // short reason phrase (e.g. "Bad Request")
        String message,            // human-readable description
        String path,               // request URI (optional, helpful for debugging)
        List<FieldError> details   // validation-field problems (may be empty)
) {
    /** Simple holder for a field-level validation error */
    public record FieldError(String field, String rejectedValue, String reason) {}
}