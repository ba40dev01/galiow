package com.badev.mynote.repository.note;


import com.badev.mynote.entity.AppUser.AppUser;
import com.badev.mynote.entity.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoteRepository extends JpaRepository<Note,Long>, JpaSpecificationExecutor<Note> {
    Iterable<Note> findByAppUser(AppUser appUser);

}
