package com.texi.app.post.repository;

import com.texi.app.domain.Post;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findAllByUser(User user);
    List<Post> findByStatus(Status status);

    @Modifying
    @Query("update Post p set p.status = 'ACTIVE' where p.id = :id")
    void enablePost(Long id);
}
