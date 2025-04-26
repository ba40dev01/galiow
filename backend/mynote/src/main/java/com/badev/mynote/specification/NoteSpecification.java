package com.badev.mynote.specification;

import com.badev.mynote.entity.AppUser.AppUser;
import com.badev.mynote.entity.note.Note;
import com.badev.mynote.entity.note.NoteCategory;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class NoteSpecification {
    public static Specification<Note> filterByAppUser(AppUser appUser){
        return ((root, query, criteriaBuilder) -> appUser == null? null: criteriaBuilder.equal(root.get("appUser"),appUser));
    }
    public static Specification<Note> filterByCategory(NoteCategory category){
        return ((root, query, criteriaBuilder) -> category == null? null: criteriaBuilder.equal(root.get("category"),category));
    }

}
