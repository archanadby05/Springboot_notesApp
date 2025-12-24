package com.example.notes.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public Map<String, Object> handleNoteNotFound(NoteNotFoundException ex, HttpServletRequest request){
        return Map.of("status", HttpStatus.NOT_FOUND.value(),
                      "message", ex.getMessage(),
                      "path", request.getRequestURI(),
                      "timestamp", Instant.now().toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        
        return Map.of(
            "status", HttpStatus.BAD_REQUEST.value(),
            "errors", errors
        );
    }
}
