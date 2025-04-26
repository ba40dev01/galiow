package com.badev.mynote.service.note;

import com.badev.mynote.dto.NoteDto;
import com.badev.mynote.entity.AppUser.AppUser;
import com.badev.mynote.entity.note.Note;
import com.badev.mynote.entity.note.NoteCategory;
import com.badev.mynote.repository.note.NoteRepository;
import com.badev.mynote.specification.NoteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;


    public String save(NoteDto dto,AppUser user){
        try {
                Note note = new Note();
                note.setAppUser(user);
                note.setTitle(dto.getTitle());
                note.setNote(dto.getNote());
                note.setCreateAt(new Date());
            if(dto.getCategory() != null){
                note.setCategory(dto.getCategory());
            }else {
                note.setCategory(NoteCategory.NONE);
            }
                note.setUpdatedAt(new Date());

                noteRepository.save(note);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Note added successfully";
    }

    public List<NoteDto> get(AppUser appUser, NoteCategory category,Long id) {
        if(id!=null){
            return noteRepository.findById(id).stream().map(NoteDto::from).toList();
        }
        Specification<Note> spec = Specification.where(NoteSpecification.filterByAppUser(appUser))
                .and(NoteSpecification.filterByCategory(category));
        return noteRepository.findAll(spec).stream()
                .map(NoteDto::from)
                .toList();
    }



    public String delete(Long id) throws Exception {
        Note note = noteRepository.findById(id).orElseThrow(() -> new Exception("Note Not Found"));
        noteRepository.delete(note);
        return "Note deleted successfully";
    }

    public String Update(Long id , NoteDto noteDto) throws Exception{
        try {
            Note note = noteRepository.findById(id).orElseThrow(() -> new Exception("Note can't be found"));
            note.setUpdatedAt(new Date());
            if(noteDto.getTitle() != null){
                note.setTitle(noteDto.getTitle());

            }
            if(noteDto.getNote() !=null){
                note.setNote(noteDto.getNote());
            }
            if (noteDto.getCategory() !=null){
                note.setCategory(noteDto.getCategory());
            }


            noteRepository.save(note);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "updated successfully";
    }

}
