package com.texi.app.domain;

import lombok.Getter;
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
    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Photo> photos;

    @OneToOne(mappedBy = "post", cascade = CascadeType.PERSIST)
    private Video video;

    @OneToMany(mappedBy = "post")
    private List<Like> likes;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    private User user;

    public Post() {
        this.date = LocalDate.now();
    }
}
