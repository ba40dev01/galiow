package com.badev.mynote.service.note;

import com.badev.mynote.dto.NoteDto;
import com.badev.mynote.entity.note.Note;
import com.badev.mynote.repository.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public String save(NoteDto dto){
        System.out.println(dto.toString());
        try {

            Note note = new Note();
            note.setTitle(dto.getTitle());
            note.setNote(dto.getNote());
            note.setCreateAt(new Date());
            note.setUpdatedAt(new Date());

            noteRepository.save(note);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Note added successfully";
    }

    public List<NoteDto> get() {
        return noteRepository.findAll().stream().map(note -> NoteDto.from(note)).toList();
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


            noteRepository.save(note);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "updated successfully";
    }

}
