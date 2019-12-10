package com.texi.app.user.service;

import com.texi.app.domain.User;

import java.util.List;

public interface UserService {

	public void save(User user);
	public List<User> findAll();
	public void update(Long id, User user);
	public User findByUsername(String email);
}
