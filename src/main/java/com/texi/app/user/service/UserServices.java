package com.texi.app.user.service;

import com.texi.app.core.Response;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;

import java.util.List;

public interface UserServices {
    Response getUser(Long id);
    Response follow(User me, Long other);
    Response unfollow(User user, String username);
    Response save(User user);

    List<User> whoToFollow(User user);
    List<User> findAll();
    void update(Long id, User user);
    User findByUsername(String email);

    void updateStatus(String username, String status);
    void updateStatus(User username, Status status);

    void update(User user);

    List<User> getFollowers(Long userId);
}
