package com.texi.app.user.controller;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.domain.User;
import com.texi.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService services;

    @Autowired
    private ResponseBuilder responseBuilder;

    @RequestMapping("/")
    public @ResponseBody Response index(){
        return responseBuilder.buildSuccess("User Area");
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
