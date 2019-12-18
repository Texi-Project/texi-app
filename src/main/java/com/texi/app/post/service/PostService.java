package com.texi.app.post.service;

import com.texi.app.domain.Advert;
import com.texi.app.domain.Post;
import com.texi.app.domain.PostData;
import com.texi.app.domain.User;

import java.util.List;

public interface PostService {
    Post save(Post post, User user);

    void logTargetAudience(Integer target, Advert advert);

    Post findById(Long id);

    List<Post> getUnhealthyPosts();

    void enablePost(Long id);

    List<Post> findAll();

    void delete(Long id);

    List<Post> findByUser(User user);

    List<Post> getPostsForUser(User user);

    List<Advert> getAdverts();

    void handlePostProcessing(PostData postData);
    List<Post> searchPostsForUser(Long id, String term);
//    List<Post> searchPostsForUser(Long id, String term, Pageable pageable);
}
