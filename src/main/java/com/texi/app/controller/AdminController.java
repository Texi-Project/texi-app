package com.texi.app.controller;

import com.texi.app.domain.Advert;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes({"user","wtf","friends","users"})
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserServices userServices;

    @Autowired
    PostService postService;

    @GetMapping("/users")
    public String users(HttpSession session, Model model){
        List<User> users = userServices.findAll();
        session.setAttribute("users",users);
        model.addAttribute("users",users);
        return "users";
    }

    @RequestMapping("/user/{u}/status/{s}")
    public String blockUser(@PathVariable("u") String username, @PathVariable("s") String status){
        userServices.updateStatus(username, status);
        return "users";
    }

    @RequestMapping("/adverts")
    public String adverts(Model model){
        List<Advert> adverts = postService.getAdverts();
        model.addAttribute("adverts", adverts);
        return "adverts";
    }
}
