package com.texi.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Advert extends Post {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Future(message = "future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;

    @Future(message = "future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stop;

    @OneToMany
    private List<User> target;
}
