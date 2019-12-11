package com.texi.app.user.service;

import com.texi.app.core.Response;
import com.texi.app.domain.User;

import java.util.List;

public interface UserServices {
    Response getUser(Long id);
    Response follow(User me, Long other);
    Response save(User user);

    public List<User> findAll();
    public void update(Long id, User user);
    public User findByUsername(String email);
}
