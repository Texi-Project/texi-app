package com.texi.app.user.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;

import java.util.List;

public interface IUserServices {
    User save(User user);

    List<User> findAll();

    User findById(Long id);

    void deleteById(Long id);
}
