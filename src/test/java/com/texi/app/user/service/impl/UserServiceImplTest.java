package com.texi.app.user.service.impl;

import com.texi.app.core.Response;
import com.texi.app.core.ResponseBuilder;
import com.texi.app.core.Translator;
import com.texi.app.domain.User;
import com.texi.app.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    UserRepository repository;

    @Mock
    ResponseBuilder responseBuilder;

    @Mock
    Translator translator;

    @InjectMocks
    UserServiceImpl services;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void followUnknownUser() {
        User user = mock(User.class);
        Response response = services.follow(user, 1L);
        Assert.that(response.getMessage().equals("user.following.not.found"), "You can not follow none existing user");
    }
//
//    @Test
//    public void follow(){
//        User user = mock(User.class);
//        user.setFirstName("Mark");
//
//        User user1 = mock(User.class);
//        user1.setFirstName("Dan Follower");
//        when(repository.findById(any())).thenReturn(Optional.of(user1));
//        Response response = services.follow(user,1L);
//        Assert.that(response.getMessage().equals("user.following.not.found"), "You can not follow none existing user");
//    }

}