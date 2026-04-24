package com.fundoonotes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteResponseDto {
    private Long id;
    private String title;
    private String description;
    private boolean pinned;
    private boolean archived;
    private boolean trashed;
}