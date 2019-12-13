package com.texi.app.comment.service;

import com.texi.app.domain.Comment;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;

import java.util.List;

public interface CommentService {
    public void save(Comment comment);

    public List<Comment> findAll();

    public void delete(Comment comment);

    public boolean deleteById(Long Id);

}
