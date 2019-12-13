package com.texi.app;

import com.texi.app.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String loadHomePage(@ModelAttribute User user) {
        return "login";
    }

    @GetMapping("/auth")
    public String login(@ModelAttribute User user) {
        return "login";
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
