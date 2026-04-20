package com.fundoonotes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notes")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean pinned;
    private boolean archived;
    private boolean trashed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}