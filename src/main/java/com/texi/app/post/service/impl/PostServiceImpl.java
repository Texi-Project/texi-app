package com.texi.app.post.service.impl;

import com.texi.app.domain.Advert;
import com.texi.app.domain.Post;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import com.texi.app.post.repository.PostRepository;
import com.texi.app.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseGet(null);
    }

    @Override
    public List<Post> getUnhealthyPosts() {
        return postRepository.findByStatus(Status.DEACTIVATED);
    }

    @Override
    public void enablePost(Long id) {
        postRepository.enablePost(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
    }

    @Override
    public List<Post> findByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    @Override
    public List<Post> getPostsForUser(User user) {
        return postRepository.getPostsForUser(user.getId());
    }

    @Override
    public List<Advert> getAdverts() {
        return postRepository.findAllAdverts();
    }
}
