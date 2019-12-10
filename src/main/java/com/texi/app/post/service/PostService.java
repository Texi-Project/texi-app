package com.texi.app.post.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    public void save(Post post);
    public List<Post> findAll();
    public void delete(Long id);
    public Post findByUser(User user);
}
