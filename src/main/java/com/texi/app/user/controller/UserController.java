package com.texi.app.user.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.User;
import com.texi.app.user.service.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices services;

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
    @ApiOperation(value = "Follow User")
    @RequestMapping(value = "/{me}/follow/{id}")
    public @ResponseBody Response follow(@PathVariable("id") String id, @PathVariable("me") String me){
        Response response = services.getUser( Long.parseLong(me));
        if(response.getCode() != ResponseCode.SUCCESS.getCode())
            return response;

        return services.follow((User) response.getData(), Long.parseLong(id));
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes ra){
        System.out.println("Here .....");
        if (result.hasErrors()){
            return "login";
        }
        Response res = services.save(user);
        ra.addFlashAttribute("savedUser",res.getData() );
        return "redirect:dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

}
