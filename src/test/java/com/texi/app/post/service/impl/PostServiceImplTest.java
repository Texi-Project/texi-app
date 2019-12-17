package com.texi.app.post.service.impl;

import com.texi.app.core.ResponseBuilder;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.post.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;

public class PostServiceImplTest {
    @Mock
    PostRepository repository;

    @Mock
    ResponseBuilder responseBuilder;

    @InjectMocks
    PostServiceImpl services;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save(){

        User user = mock(User.class);
        user.setUsername("user");

        Post post = mock(Post.class);
        post.setDescription("Hello you shit shitty guy if you are fucking crazy fuck");
        post.setTitle("Hello you shit shitty guy if you are fucking crazy fuck");

        services.save(post,user);

    }

}