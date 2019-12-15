package com.texi.app.post.service.impl;

import com.texi.app.domain.*;
import com.texi.app.post.repository.PostRepository;
import com.texi.app.post.service.PostService;
import com.texi.app.user.repository.UserRepository;
import com.texi.app.utility.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    PostRepository postRepository;
    UserRepository userRepository;
    Upload upload;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, Upload upload) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.upload = upload;
    }

    @Override
    public Post save(Post post, User user) {
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseGet(null);
    }

    @Override
    public List<Post> getUnhealthyPosts() {
        return postRepository.findByStatus(Status.DEACTIVATED);
    }

    @Override
    public void enablePost(Long id) {
        postRepository.enablePost(id);
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
        return postRepository.findAllByUserOrderByDateDesc(user.getId());
    }

    @Override
    public List<Post> getPostsForUser(User user) {
        return postRepository.getPostsForUser(user.getId());
    }

    @Override
    public List<Advert> getAdverts() {
        return postRepository.findAllAdverts();
    }

    @Override
    public void handlePostProcessing(PostData postData) {
        // the big assumption here is that the @Transactional annotation will take care of the persisting, and/or merging for us
        Post post = postRepository.getOne(postData.getPostId());

        if (postData.getNotify()) {
            String fullName = String.format("%s %s", post.getUser().getFirstName(), post.getUser().getLastName());
            logger.info("notify {}'s followers", fullName);
            String text = String.format("%s just posted on Texi. Click <a href='/user/timeline/%d'>here</a> to view post.",
                    fullName, post.getUser().getId());
            Notification notification = new Notification();
            notification.setText(text);

            List<User> followers = userRepository.getFollowers(post.getUser().getId());
            followers.stream().forEach(f -> {
                List<Notification> notifications = new ArrayList<>();
                notifications.add(notification);
                f.setNotifications(notifications);
            });
        }
    }
}
