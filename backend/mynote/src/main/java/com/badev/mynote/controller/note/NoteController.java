package com.badev.mynote.controller.note;

import com.badev.mynote.dto.NoteDto;
import com.badev.mynote.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public List<NoteDto> get(){
        return noteService.get();
    }
    @PostMapping
    public String add(@RequestBody  NoteDto dto){

        return noteService.save(dto);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) throws Exception {
        return noteService.delete(id);
    }
    @PatchMapping("{id}")
    public String update(@PathVariable Long id, @RequestBody NoteDto dto) throws Exception {
        return noteService.Update(id,dto);
    }

}
