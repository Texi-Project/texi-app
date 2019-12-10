package com.texi.app.user.service;

import com.texi.app.core.Response;
import com.texi.app.domain.User;

public interface UserServices {
    Response getUser(Long id);
    Response follow(User me, Long other);
}
