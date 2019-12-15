package com.texi.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
//@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, message = "size.min")
    @Column(unique = true)
    private String username;

    @Size(min = 5, message = "size.min")
    private String password;

    @Size(min = 2, message = "size.min")
    private String firstName;

    @Size(min = 2, message = "size.min")
    private String lastName;

    private String photoUrl;

    @Past(message = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @OneToMany
    private Set<User> following;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    public User() {
        this.following = new HashSet<>();
        this.status = Status.ACTIVE;
        this.roles = new HashSet<>();
    }

    public void addToFollowing(User user) {
        following.add(user);
    }
}
