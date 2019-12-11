package com.texi.app.comment.controller;

import com.texi.app.comment.service.CommentService;
import com.texi.app.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")//to be modified when needed
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")// to be modified to implement PRG pattern
    public void addComment(@RequestBody Comment comment){
       commentService.save(comment);
    }
    @PostMapping("/delete/{id}")// to be modified to implement PRG pattern
    public boolean delete(@PathVariable Long id){
        return commentService.deleteById(id);
    }


}
