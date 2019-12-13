package com.texi.app.like.service;

import com.texi.app.domain.Comment;
import com.texi.app.domain.Like;

import java.util.List;

public interface LikeService {
    public void save(Like like);

    public List<Like> findAll();

    public void delete(Like like);

    public boolean deleteById(Long Id);

}
