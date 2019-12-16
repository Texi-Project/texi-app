package com.texi.app.user.service.impl;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.core.Translator;
import com.texi.app.domain.Role;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import com.texi.app.user.repository.RoleRepository;
import com.texi.app.user.repository.UserRepository;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class UserServiceImpl implements UserServices {

    private UserRepository repository;
    private RoleRepository roleRepository;
    private Translator translator;
    private ResponseBuilder responseBuilder;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository,
                           Translator translator, ResponseBuilder responseBuilder, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.translator = translator;
        this.responseBuilder = responseBuilder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Response getUser(Long id) {
        Optional<User> user = repository.findById(id);
        if(!user.isPresent())
            return responseBuilder.buildFail(ResponseCode.NOT_FOUND,
                    translator.getMessage("user.following.not.found"));

        return responseBuilder.buildSuccess(user.get());
    }

    @Override
    public Response follow(User me, Long other) {

        Optional<User> following = repository.findById(other);
        if(!following.isPresent())
            return responseBuilder.buildFail("user.following.not.found");

        //@todo may not be necessary, hibernate may fail if duplicates appear
        System.out.println("UserServiceImpl me:"+me.getId()+" other: "+other);
        User fo = repository.findFollowing(me.getId(), other);
        if(fo!=null)
            return responseBuilder.buildFail(String.format(translator.getMessage("user.following.already"),
                    fo.getFirstName()));

        User toFollow = following.get();
        me.addToFollowing(toFollow);
        repository.save(me);

        return responseBuilder.buildSuccess(String.format(translator.getMessage("user.follow.success"),
                toFollow.getFirstName()));
    }

    @Override
    public Response unfollow(User user, String username) {
        User removable = findByUsername(username);
        user.getFollowing().remove(removable);
        return responseBuilder.buildSuccess("removed");
    }

    @Override
    public Response save(User user){
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setStatus(Status.ACTIVE);
        Role userRole = roleRepository.findByRole("ROLE_USER");
        Set<Role> roles  = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        repository.save(user);
        return responseBuilder.buildSuccess(translator.getMessage("user.success"), user);
    }

    @Override
    public List<User> whoToFollow(User user) {
        List<User> users = repository.whoToFollow(user.getId());
        return users != null ? users : new ArrayList<>();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void update(Long id, User user) {

    }

    @Override
    public User findByUsername(String email) {
        return repository.findByUsername(email);
    }

    @Override
    public void updateStatus(String username, String status) {
        User user = findByUsername(username);
        user.setStatus(status.equals("active")? Status.ACTIVE: Status.DEACTIVATED);
        repository.save(user);
    }

    @Override
    public void updateStatus(User user, Status status) {
        user.setStatus(status);
        repository.save(user);
    }

    @Override
    public void update(User user) {

    }
}
