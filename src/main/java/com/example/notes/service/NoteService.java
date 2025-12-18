package com.example.notes.service;

import com.example.notes.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final List<Note> notes = new ArrayList<>();
    private long nextId = 0;

    public Note create(Note note){
        note.setId(++nextId);
        notes.add(note);

        return note;
    }

    public List<Note> getAll(){
        return notes;
    }

    public Optional<Note> getById(Long id){
        return notes.stream().filter(note -> note.getId().equals(id)).findFirst();
    }
}
