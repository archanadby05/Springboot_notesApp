package com.example.notes.repository;

import com.example.notes.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    
    Page<Note> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
