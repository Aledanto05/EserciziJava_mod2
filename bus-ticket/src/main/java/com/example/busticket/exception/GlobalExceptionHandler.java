package com.example.busticket.config;

import com.example.busticket.exception.InsufficientCreditException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientCreditException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficient(InsufficientCreditException ex) {
        return ResponseEntity.unprocessableEntity().body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", 422,
                "error", "Unprocessable Entity",
                "message", "Credito insufficiente"
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegal(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", 400,
                "error", "Bad Request",
                "message", ex.getMessage()
        ));
    }
}
