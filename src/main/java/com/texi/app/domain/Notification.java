package com.texi.app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "string.notEmpty")
    private String text;

    private LocalDateTime notifyTime;

    @ManyToMany(mappedBy = "notifications", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> target;

    @Transient
    private User owner;
}
