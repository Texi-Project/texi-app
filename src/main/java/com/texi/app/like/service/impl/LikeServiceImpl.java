package com.texi.app.like.service.impl;

import com.texi.app.domain.Like;
import com.texi.app.like.repository.LikeRepository;
import com.texi.app.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {


    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void save(Like like) {
        likeRepository.save(like);
    }

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public void delete(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public boolean deleteById(Long id) {
        likeRepository.deleteById(id);
        return true;
    }
}
