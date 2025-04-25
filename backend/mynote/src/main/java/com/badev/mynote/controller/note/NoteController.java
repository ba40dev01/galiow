package com.badev.mynote.controller.note;

import com.badev.mynote.dto.NoteDto;
import com.badev.mynote.entity.AppUser.AppUser;
import com.badev.mynote.service.TokenService;
import com.badev.mynote.service.note.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final TokenService tokenService;

    @GetMapping
    public List<NoteDto> get(){
        return noteService.get();
    }
    @PostMapping
    public String add(@RequestBody NoteDto dto, HttpServletRequest request) throws Exception {
        AppUser appUser = tokenService.getAppUserFromToken(request);
        return noteService.save(dto,appUser);
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
