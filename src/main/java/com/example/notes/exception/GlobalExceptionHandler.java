package com.example.notes.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public Map<String, Object> handleNoteNotFound(NoteNotFoundException ex, HttpServletRequest request){
        return Map.of("status", HttpStatus.NOT_FOUND.value(),
                      "message", ex.getMessage(),
                      "path", request.getRequestURI(),
                      "timestamp", Instant.now().toString());
    }
}
