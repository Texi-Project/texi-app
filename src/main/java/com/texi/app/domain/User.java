package com.texi.app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
//@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, message = "{size.min}")
    @Column(unique = true)
    @Email(message = "email")
    private String username;

    @Size(min = 4, message = "{size.min}")
    private String password;

    @Size(min = 2, message = "{size.min}")
    private String firstName;

    @Size(min = 2, message = "{size.min}")
    private String lastName;

    private String photoUrl;

//    @Past(message = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @OneToMany
    private Set<User> following;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Flag> flags;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Set<Claim> claims;

    public User() {
        this.following = new HashSet<>();
        this.status = Status.ACTIVE;
        this.roles = new HashSet<>();
        this.flags = new ArrayList<>();
        this.claims = new HashSet<>();
    }

    public void addToFollowing(User user) {
        following.add(user);
    }
    public void addFlag(Flag flag){
        flag.setUser(this);
        flags.add(flag);
    }

    public void addRole(Role role){
        role.setUser(this);
        roles.add(role);
    }

    @JsonBackReference
    @JsonManagedReference
    public Set<User> getFollowing() {
        return following;
    }
}
