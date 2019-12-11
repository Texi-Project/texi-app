package com.texi.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name="[like]")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String emoji;

    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;

    public Like() {
        this.date = LocalDateTime.now();
    }
}
