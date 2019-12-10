package com.texi.app.post.repository;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAll();

    public List<Post> findAllByUser(User user);
}
