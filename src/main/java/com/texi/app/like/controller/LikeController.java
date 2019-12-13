package com.texi.app.like.controller;

import com.texi.app.domain.Like;
import com.texi.app.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/like")// to be modified to implement PRG pattern
    public void like(@RequestBody Like like) {
        likeService.save(like);
    }

    @PostMapping("/unlike/{id}")// to be modified to implement PRG pattern
    public boolean unlike(@PathVariable Long id) {
        return likeService.deleteById(id);
    }
}
