package com.texi.app.notifications.controller;

import com.texi.app.domain.Notification;
import com.texi.app.domain.User;
import com.texi.app.notifications.service.NotifyService;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/notifications")
@SessionAttributes({"user", "wtf", "friends", "followers", "notifications"})
public class NotificationController {

    @Autowired
    NotifyService notifyService;

    @Autowired
    UserServices userService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal == null) return "redirect:/auth";
        User u = userService.findByUsername(principal.getName());

        List<Notification> notifications = notifyService.getNotificationsForUser(u.getId());
        model.addAttribute("notifications", notifications);

        return "notifications";
    }
}
