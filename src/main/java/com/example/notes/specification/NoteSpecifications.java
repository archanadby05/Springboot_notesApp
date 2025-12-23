package com.example.notes.specification;

import com.example.notes.model.Note;
import org.springframework.data.jpa.domain.Specification;

public class NoteSpecifications {

    public static Specification<Note> titleContains(String title){
        return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Note> contentContains(String content){
        return (root, query, cb) -> cb.like(cb.lower(root.get("content")), "%" + content.toLowerCase() + "%");
    }
}
