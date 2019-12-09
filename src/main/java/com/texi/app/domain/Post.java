package com.texi.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "string.notEmpty")
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

    public Post() {
        this.date = LocalDate.now();
    }
}
