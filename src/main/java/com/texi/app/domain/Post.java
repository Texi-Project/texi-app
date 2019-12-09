package com.texi.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate date;
    private String description;
    @OneToOne
    private Text text;

    private Status status;
    @OneToMany
    private List<Photo> photos;

    @OneToOne
    private Video video;

    @OneToMany
    private List<Like> likes;

    @OneToMany
    private List<Comment> comments;
}
