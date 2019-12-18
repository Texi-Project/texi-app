package com.texi.app.controller;

import com.texi.app.claim.repository.ClaimRepository;
import com.texi.app.claim.service.ClaimService;
import com.texi.app.domain.Advert;
import com.texi.app.domain.Claim;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
//@SessionAttributes({"user","wtf","friends","users"})
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserServices userServices;

    @Autowired
    PostService postService;

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    ClaimService claimService;

    @GetMapping("/users")
    public String users(HttpSession session, Model model){
        List<User> users = userServices.findAll();
//        session.setAttribute("users",users);
        model.addAttribute("users",users);
        return "users";
    }

    @RequestMapping("/user/{username}/status/{status}")
    public String blockUser(@PathVariable("username") String username, @PathVariable("status") String status){
        userServices.updateStatus(username, status);
        return "users";
    }

    @RequestMapping("/claim/enable")
    public String blockUser(@RequestParam("c") String id){
        Claim claim = claimService.resolveClaim(Long.valueOf(id));
        if(claim!=null) {
            userServices.updateStatus(claim.getUser(), Status.ACTIVE);
        }
        return "claims";
    }

    @RequestMapping("/adverts")
    public String adverts(Model model){
        List<Advert> adverts = postService.getAdverts();
        model.addAttribute("adverts", adverts);
        return "adverts";
    }

    @GetMapping("/claims")
    public String claims(HttpSession session, Model model){
        List<User> users = userServices.findAll();
//        session.setAttribute("users",users);
        model.addAttribute("claims",claimRepository.findByStatusOrderByClaimDateDesc(Status.ACTIVE));
        return "claims";
    }

}
