package com.texi.app.user.service.impl;

import com.texi.app.domain.User;
import com.texi.app.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(Long id, User user) {

    }

    @Override
    public User findByUsername(String email) {
        return null;
    }
}
