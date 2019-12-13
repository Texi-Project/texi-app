package com.texi.app.post.service.impl;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.post.repository.PostRepository;
import com.texi.app.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    private static String UPLOADS_LOCATION = "/tmp/";

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(UPLOADS_LOCATION + multipartFile.getOriginalFilename());
        Files.write(path, bytes);
        System.out.printf("%s upload successful", multipartFile.getOriginalFilename());
        return UPLOADS_LOCATION + multipartFile.getOriginalFilename(); // return absolute filename
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseGet(null);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
    }

    @Override
    public List<Post> findByUser(User user) {
        return postRepository.findAllByUser(user);
    }
}
