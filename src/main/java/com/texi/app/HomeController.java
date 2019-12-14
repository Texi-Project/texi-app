package com.texi.app;

import com.texi.app.comment.service.CommentService;
import com.texi.app.domain.Comment;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

//    @GetMapping("/")
//    public String loadHomePage() {
//        return "login";
//    }
    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;

    @GetMapping({"/", "/auth"})
    public String login(@ModelAttribute User user) {
//        List<Comment> comments=commentService.findByPost(postService.findById(2l));
//        for(Comment comment:comments)
//            System.out.println(comment.getId());
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/logout/")
    public String logout() {
        return "logout";
    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }

    @GetMapping("/sample-form/")
    public String sampleForm() {
        return "sample-form";
    }
}
