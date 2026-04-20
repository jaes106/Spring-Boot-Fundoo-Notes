package com.fundoonotes.service;

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;

import java.util.List;

public interface NoteService {
    NoteResponseDto create(NoteRequestDto dto, String token);
    List<NoteResponseDto> getAll(String token);
}