package com.badev.mynote.repository.note;


import com.badev.mynote.entity.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {

}
