package com.texi.app.comment.service.impl;

import com.texi.app.comment.repository.CommentRepository;
import com.texi.app.comment.service.CommentService;
import com.texi.app.domain.Comment;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public boolean deleteById(Long id) {
        commentRepository.deleteById(id);
        return true;
    }
}
