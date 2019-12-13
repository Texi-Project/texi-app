package com.texi.app.user.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import com.texi.app.security.UserDetailsServiceImpl;
import com.texi.app.user.service.UserServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    public static String uploadDirectory = System.getProperty("user.dir")+"/photoUploads";

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserServices services;

    @Autowired
    private PostService postService;

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
    public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes ra,
                         @RequestParam("photo") MultipartFile photo, Model model){

        if (bindingResult.hasErrors()){
            return "login";
        }

        User u = services.findByUsername(user.getUsername());

        if (u != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
            return "login";
        }

        //StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(uploadDirectory, photo.getOriginalFilename());
        //fileNames.append(photo.getOriginalFilename()+" ");
        try {
            Files.write(fileNameAndPath, photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setPhotoUrl(fileNameAndPath.toString());
        Response res = services.save(user);

        ra.addFlashAttribute("user",res.getData());
        model.addAttribute("user", res.getData());
        return "redirect:auth";
    }

    @GetMapping(value = {"/dashboard", "/timeline"})
    public String dashboard(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:auth";
        }
        System.out.println("Principle: "+principal.getName());

        User u = services.findByUsername(principal.getName());
        model.addAttribute("user", u);

        List<Post> postList = postService.findByUser(u);
        model.addAttribute("posts", postList);

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
