package com.texi.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, message = "size.min")
    @Column(unique = true)
    private String username;

    private String password;

    @Size(min = 2, message = "size.min")
    private String firstName;

    @Size(min = 2, message = "size.min")
    private String lastName;

    private String photoUrl;

    @Past(message = "birthday")
    private LocalDate birthday;

    @OneToMany
    private Set<User> following;

    @OneToMany
    private List<Comment> comments=new ArrayList<>();

    public User(){
        this.following = new HashSet<>();
    }

    public void addToFollowing(User user){
        following.add(user);
    }
}
