package com.texi.app.post.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;

import java.util.List;

public interface PostService {
    void save(Post post);

    Post findById(Long id);

    List<Post> findAll();

    void delete(Long id);

    List<Post> findByUser(User user);
}
