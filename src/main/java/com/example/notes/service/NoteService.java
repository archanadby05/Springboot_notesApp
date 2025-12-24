    package com.example.notes.service;

    import com.example.notes.model.Note;
    import com.example.notes.specification.NoteSpecifications;
    import com.example.notes.repository.NoteRepository;
    import com.example.notes.dto.UpdateNoteRequest;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.domain.Specification;
    import org.springframework.stereotype.Service;

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

        public Page<Note> getAll(String title, String content, Pageable pageable){

            Specification<Note> spec = Specification.unrestricted();

            if(title != null && !(title.isBlank())){
                spec = spec.and(NoteSpecifications.titleContains(title));
            }

            if(content != null && !(content.isBlank())){
                spec = spec.and(NoteSpecifications.contentContains(content));
            }
            
            return noteRepository.findAll(spec, pageable);
        }

        public Optional<Note> getById(Long id){
            return noteRepository.findById(id);
        }

        public Optional<Note> patch(Long id, UpdateNoteRequest updates){
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
