package com.texi.app.post.service;

import com.texi.app.domain.*;

import java.util.List;

public interface PostService {
    Post save(Post post, User user);

    Post findById(Long id);

    List<Post> getUnhealthyPosts();

    void enablePost(Long id);

    List<Post> findAll();

    void delete(Long id);

    List<Post> findByUser(User user);

    List<Post> getPostsForUser(User user);

    List<Advert> getAdverts();

    void handlePostProcessing(PostData postData);
}
