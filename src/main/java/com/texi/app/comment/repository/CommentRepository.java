package com.texi.app.comment.repository;

import com.texi.app.domain.Comment;
import com.texi.app.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

        public List<Comment> findByPost(Post post);

}
