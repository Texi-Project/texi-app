package com.texi.app.user.service.impl;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.ResponseCode;
import com.texi.app.core.Translator;
import com.texi.app.domain.User;
import com.texi.app.user.repository.UserRepository;
import com.texi.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private Translator translator;
    private ResponseBuilder responseBuilder;

    @Autowired
    public UserServiceImpl(UserRepository repository, Translator messageHelper, ResponseBuilder responseBuilder) {
        this.repository = repository;
        this.translator = messageHelper;
        this.responseBuilder = responseBuilder;
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
    public Response save(User user){
        repository.save(user);
        return responseBuilder.buildSuccess(translator.getMessage("user.success"), user);
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
}
