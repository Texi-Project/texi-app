package com.texi.app.post.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.Photo;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.domain.Video;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
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
    Response index() { // no use case for this though
        List<Post> postList = postService.findAll();
        return responseBuilder.buildSuccess(postList);
    }

    @PostMapping("/add")
    public RedirectView add(@RequestParam("title") String title, @RequestParam("image") MultipartFile image,
                            @RequestParam("video") MultipartFile video, Principal principal) throws IOException {
        if (title.equals("") || image.isEmpty() && video.isEmpty()) {
            System.out.println("Blank post received, ignoring...");
            return new RedirectView("/user/dashboard");
        }
        List<Photo> photos = new ArrayList<>();
        if (!image.isEmpty()) {
            photos.add(new Photo(postService.upload(image)));
        }
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(title);
        post.setPhotos(photos);
        post.setVideo(video.isEmpty() ? null : new Video(postService.upload(video)));
        if (principal == null) {
            return new RedirectView("/auth");
        }
        User user = userService.findByUsername(principal.getName());
        post.setUser(user);
        postService.save(post);
        return new RedirectView("/user/dashboard");
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
    Response delete(@PathVariable String postId) {
        postService.delete(Long.parseLong(postId));
        return responseBuilder.buildSuccess();
    }

}
