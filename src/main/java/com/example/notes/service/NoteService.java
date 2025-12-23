package com.example.notes.service;

import com.example.notes.model.Note;
import org.springframework.stereotype.Service;
import com.example.notes.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note create(Note note){
        return noteRepository.save(note);
    }

    public List<Note> getAll(){
        return noteRepository.findAll();
    }

    public Optional<Note> getById(Long id){
        return noteRepository.findById(id);
    }

    public Optional<Note> patch(Long id, Note updates){
        return noteRepository.findById(id).map(existing -> {
            if(updates.getTitle() != null){
                existing.setTitle(updates.getTitle());
            }

            if(updates.getContent() != null){
                existing.setContent(updates.getContent());
            }

            return noteRepository.save(existing);
        });
    }

    public boolean deleteById(Long id){
        if(!noteRepository.existsById(id)){
            return false;
        }
        
        noteRepository.deleteById(id);
        return true;
    }

}
