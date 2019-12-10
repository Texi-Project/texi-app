package com.texi.app.post.service.impl;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Override
    public void save(Post post) {

    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Post findByUser(User user) {
        return null;
    }
}
