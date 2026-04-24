package com.fundoonotes.service.impl;

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;
import com.fundoonotes.entity.Note;
import com.fundoonotes.entity.User;
import com.fundoonotes.repository.*;
import com.fundoonotes.service.NoteService;
import com.fundoonotes.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    public NoteServiceImpl(NoteRepository noteRepository,
                           UserRepository userRepository,
                           TokenUtil tokenUtil) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    private User getUser(String token) {
        Long id = tokenUtil.decodeToken(token);
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public NoteResponseDto create(NoteRequestDto dto, String token) {
        User user = getUser(token);

        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        note.setUser(user);

        Note saved = noteRepository.save(note);

        return new NoteResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isPinned(),
                saved.isArchived(),
                saved.isTrashed()
        );
    }

    @Override
    public List<NoteResponseDto> getAll(String token) {
        User user = getUser(token);

        return noteRepository.findByUserId(user.getId())
                .stream()
                .map(n -> new NoteResponseDto(
                        n.getId(),
                        n.getTitle(),
                        n.getDescription(),
                        n.isPinned(),
                        n.isArchived(),
                        n.isTrashed()
                ))
                .collect(Collectors.toList());
    }
}