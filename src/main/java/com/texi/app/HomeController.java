package com.texi.app;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class HomeController {
    private final PostService postService;
    private final UserServices userService;

    @Autowired
    public HomeController(PostService postService, UserServices userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String loadHomePage(@ModelAttribute User user) {
        return "login";
    }

    @GetMapping("/auth")
    public String login(@ModelAttribute User user) {
        return "login";
    }

    @GetMapping(value = {"/dashboard", "/timeline"})
    @ResponseStatus(HttpStatus.OK)
    public String dashboard(Model model) {
        Response response = userService.getUser(1L); // todo use Principal object in session
        if (response.getCode() != ResponseCode.SUCCESS.getCode()) return "dashboard";
        List<Post> postList = postService.findByUser((User) response.getData());
        model.addAttribute("user", response.getData());
        model.addAttribute("posts", postList);
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

//    @GetMapping("/dashboard")
//    public String dashboard(@ModelAttribute User user) {
//        return "dashboard";
//    }
}
