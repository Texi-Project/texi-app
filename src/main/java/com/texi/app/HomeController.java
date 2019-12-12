package com.texi.app;

import com.texi.app.domain.User;
import org.omg.CORBA.UnknownUserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.model.IModel;

@Controller
public class HomeController {

    @GetMapping("/")
    public String loadHomePage() {
        return "login";
    }

    @GetMapping("/auth")
    public String login(@ModelAttribute User user) {
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
