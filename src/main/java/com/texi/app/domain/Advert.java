package com.texi.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Advert extends Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future(message = "future")
    private LocalDateTime start;

    @Future(message = "future")
    private LocalDateTime stop;

    @OneToMany
    private List<User> target;
}
