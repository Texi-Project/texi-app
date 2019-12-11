package com.texi.app.post.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserServices userService;
    private final ResponseBuilder responseBuilder;

    @Autowired
    public PostController(PostService postService, UserServices userService, ResponseBuilder responseBuilder) {
        this.postService = postService;
        this.userService = userService;
        this.responseBuilder = responseBuilder;
    }

    @GetMapping("/")
    public @ResponseBody
    Response index() {
        return responseBuilder.buildSuccess("Posts Index");
    }

    @PostMapping("/add")
    public @ResponseBody
    Response add(Post post) {
        postService.save(post);
        return responseBuilder.buildSuccess();
    }

    @PostMapping("/getAll")
    public @ResponseBody
    Response getAll() {
        List<Post> postList = postService.findAll();
        return responseBuilder.buildSuccess(postList);
    }

    @PostMapping("/getAll/{userId}")
    public @ResponseBody
    Response getByUser(@PathVariable String userId) {
        // todo: review what should be returned by service
        Response response = userService.getUser(Long.parseLong(userId));
        if (response.getCode() != ResponseCode.SUCCESS.getCode())
            return response;
        List<Post> postList = postService.findByUser((User) response.getData());
        return responseBuilder.buildSuccess(postList);
    }

    @PostMapping("/delete/{postId}")
    public @ResponseBody
    Response add(@PathVariable String postId) {
        postService.delete(Long.parseLong(postId));
        return responseBuilder.buildSuccess();
    }

}
