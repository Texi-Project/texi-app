package com.texi.app.user.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.User;
import com.texi.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService services;

    @Autowired
    private ResponseBuilder responseBuilder;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "James Bond");
        model.addAttribute("message", "Hello World");
        model.addAttribute("tasks", "Digging");
        return "index";
    }

    // @todo extract user from principle object once implemented
    @RequestMapping(value = "/{me}/follow/{id}")
    public @ResponseBody Response follow(@PathVariable("id") String id, @PathVariable("me") String me){
        Response response = services.getUser( Long.parseLong(me));
        if(response.getCode() != ResponseCode.SUCCESS.getCode())
            return response;

        return services.follow((User) response.getData(), Long.parseLong(id));
    }

    @PostMapping("/create")
    public @ResponseBody Response create(@Valid @RequestBody User user){
        return services.save(user);
    }

}
