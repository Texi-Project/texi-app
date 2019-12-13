package com.texi.app.post.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostService  {
   void save(Post post);

   Post findById(Long id);

    List<Post> findAll();

    void delete(Long id);

    List<Post> findByUser(User user);

}
