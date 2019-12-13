package com.texi.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ManyToOne
    private User user;
    @NotEmpty(message = "string.notEmpty")
    private String text;

    @ManyToOne
    private Post post;

    public Comment() {
        this.date = LocalDateTime.now();
    }
}
