package com.badev.mynote.entity.note;

import com.badev.mynote.entity.AppUser.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String note;
    private NoteCategory category;
    private Date createAt;
    private Date updatedAt;

    @ManyToOne
    private AppUser appUser;
}
