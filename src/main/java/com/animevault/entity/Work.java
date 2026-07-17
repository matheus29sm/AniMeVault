package com.animevault.entity;

import com.animevault.enums.AnimeStatus;
import com.animevault.enums.NotesStatus;
import com.animevault.enums.ReadingFormat;
import com.animevault.enums.ReadingStatus;
import com.animevault.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rank;

    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "anime_status")
    private AnimeStatus animeStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "reading_format")
    private ReadingFormat readingFormat;

    @Enumerated(EnumType.STRING)
    @Column(name = "reading_status")
    private ReadingStatus readingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "notes_status")
    private NotesStatus notesStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_reading_status")
    private UserStatus userStatus;

}