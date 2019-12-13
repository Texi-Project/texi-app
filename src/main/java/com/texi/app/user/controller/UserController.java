package com.texi.app.user.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.User;
import com.texi.app.security.UserDetailsImpl;
import com.texi.app.security.UserDetailsServiceImpl;
import com.texi.app.user.service.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

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
    public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes ra){
        if (bindingResult.hasErrors()){
            return "login";
        }

        UserDetails u = userDetailsService.loadUserByUsername(user.getUsername());
        if (u != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the email provided");
            return "login";
        }

        Response res = services.save(user);
        ra.addFlashAttribute("savedUser",res.getData() );
        return "redirect:dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        System.out.println("Principle: "+principal.getName());
        User u = services.findByUsername(principal.getName());
        model.addAttribute("user", u);
        return "dashboard";
    }


    @GetMapping("/auth")
    public String auth(@ModelAttribute User user) {
        return "login";
    }

    @RequestMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @ModelAttribute User user, Model model, Principal principal) {
        System.out.println("Login.....");
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
            System.out.println("Principle: login "+principal.getName());
            return "dashboard";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

}
