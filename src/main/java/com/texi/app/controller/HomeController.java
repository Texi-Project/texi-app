package com.texi.app.controller;

import com.texi.app.core.Response;
import com.texi.app.domain.Claim;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import com.texi.app.utility.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    private UserServices services;

    @Autowired
    private PostService postService;

    @Autowired
    private Upload upload;

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

        String fileNameAndPath = upload.upload(photo);
        user.setPhotoUrl(fileNameAndPath);
        Response res = services.save(user);

        ra.addFlashAttribute("user",res.getData());
        model.addAttribute("user", res.getData());
        return "redirect:auth";
    }


    @GetMapping("/claim")
    public String claim(@ModelAttribute String response, Model model) {
        return "claim";
    }

    @PostMapping("/claim")
    public String claimReceive(@RequestParam("email") String email, @RequestParam("reason") String reason,
                               RedirectAttributes redirectAttributes, Model model) {
        User user = services.findByUsername(email);
        if(user==null){
            model.addAttribute("response", "User not found");
            return "claim";
        }

        Claim claim = new Claim();
        claim.setUser(user);
        claim.setReason(reason);
        claim.setStatus(Status.ACTIVE);
        claim.setClaimDate(LocalDate.now());
        redirectAttributes.addFlashAttribute("response", services.saveClaim(claim).getMessage());
        return "redirect:/claim";
    }
}
