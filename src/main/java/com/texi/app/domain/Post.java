package com.texi.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "string.notEmpty")
    private String title;
    private LocalDateTime date;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<Photo> photos;

    @OneToOne(mappedBy = "post", cascade = CascadeType.PERSIST)
    private Video video;

    @OneToMany(mappedBy = "post")
    private List<Like> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Comment> comments;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Flag flag;

    public Post() {
        this.date = LocalDateTime.now();
    }
}
