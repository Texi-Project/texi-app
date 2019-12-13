package com.texi.app.post.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    void save(Post post);

    String upload(MultipartFile multipartFile) throws IOException;

    Post findById(Long id);

    List<Post> findAll();

    void delete(Long id);

    List<Post> findByUser(User user);
}
