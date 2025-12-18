package com.example.notes.controller;

import java.util.List;
import com.example.notes.model.Note;
import com.example.notes.service.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note){
        return noteService.create(note);
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteService.getAll();
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable Long id){
        return noteService.getById(id).orElseThrow(() -> new RuntimeException("Note Not Found"));
    }
}
