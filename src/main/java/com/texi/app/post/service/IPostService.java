package com.texi.app.post.service;

import com.texi.app.domain.Post;

import java.util.List;

public interface IPostService {
    Post save(Post post);

    List<Post> findAll();

    Post findById(Long id);

    void deleteById(Long id);
}
