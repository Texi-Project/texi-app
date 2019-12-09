package com.texi.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, message = "size.min")
    private String username;

    private String password;

    @Size(min = 2, message = "size.min")
    private String firstName;

    @Size(min = 2, message = "size.min")
    private String lastName;

    private String photoUrl;

    @Past(message = "birthday")
    private LocalDate birthday;
}
