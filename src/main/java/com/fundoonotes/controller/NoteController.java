package com.fundoonotes.controller;

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;
import com.fundoonotes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponseDto> create(
            @Valid @RequestBody NoteRequestDto dto,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.create(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> getAll(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(noteService.getAll(token));
    }
}