package com.texi.app.post.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;

import java.util.List;

public interface PostService {
    public void save(Post post);

    public List<Post> findAll();

    public void delete(Post post);

    public List<Post> findByUser(User user);
}
