package com.texi.app.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.domain.Post;
import com.texi.app.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("client")
public class ClientController {

    @Autowired
    private PostService postService;

    @Autowired
    private ResponseBuilder responseBuilder;

    @GetMapping(value = "/posts", produces = "application/json")
    public @ResponseBody Response posts() {
        List<Post> postList = postService.findAll();
        return responseBuilder.buildSuccess(postList);
    }
}
