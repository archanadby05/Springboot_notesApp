package com.example.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateNoteRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @NotBlank(message = "Content must not be blank")
    @Size(max = 1000, message = "Title must be at most 1000 characters")
    private String content;

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
