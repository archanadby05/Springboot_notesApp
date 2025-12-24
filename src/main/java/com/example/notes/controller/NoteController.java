package com.example.notes.controller;

import com.example.notes.model.Note;
import com.example.notes.service.NoteService;
import com.example.notes.dto.PageResponse;
import com.example.notes.exception.NoteNotFoundException;
import com.example.notes.dto.CreateNoteRequest;
import com.example.notes.dto.UpdateNoteRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody CreateNoteRequest request){
        Note note = new Note();
        
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        return noteService.create(note);
    }

    @GetMapping("/notes")
    public PageResponse<Note> getAllNotes(@RequestParam(required = false) String title, 
                                        @RequestParam(required = false) String content, 
                                        @PageableDefault(size = 10, sort = "id") Pageable pageable){
        
        Page<Note> page = noteService.getAll(title, content, pageable);

        return new PageResponse<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @PatchMapping("/notes/{id}")
    public Note patchNote(@PathVariable Long id, @Valid @RequestBody UpdateNoteRequest request){
        return noteService.patch(id, request).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable Long id){
        boolean deleted = noteService.deleteById(id);
        if(!deleted){
            throw new NoteNotFoundException(id);
        }
    }
}
