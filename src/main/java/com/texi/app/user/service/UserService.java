package com.texi.app.user.service;

import com.texi.app.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserServices{
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
