package com.badev.mynote.dto;

import com.badev.mynote.entity.note.Note;
import com.badev.mynote.entity.note.NoteCategory;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NoteDto {
    private Long id;
    private String title;
    private String note;
    private NoteCategory category;
    private Date createAt;
    private Date updatedAt;

    private static NoteDto.NoteDtoBuilder map(Note note){
        return NoteDto.builder()
                .id(note.getId())
                .category(note.getCategory())
                .note(note.getNote())
                .title(note.getTitle())
                .createAt(note.getCreateAt())
                .updatedAt(note.getUpdatedAt());

    }

    public static NoteDto from(Note note){
        return map(note).build();

    }


}
