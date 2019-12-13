package com.texi.app.post.service.impl;
import com.texi.app.domain.Post;
import com.texi.app.domain.Text;
import com.texi.app.domain.User;
import com.texi.app.post.repository.PostRepository;
import com.texi.app.post.service.SearchPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service

public class SearchPostImpl implements SearchPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    @Override
    public Post findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    @Override
    public List<Post> findAllByDescriptionOrderByDateAsc(String desc) {
        return postRepository.findAllByDescriptionOrderByDateAsc(desc);
    }

    @Override
    public List<Post> findAllByDescriptionContains(String searchWord) {
        return postRepository.findAllByDescriptionContains(searchWord);
    }

    @Override
    public List<Post> findAllByTitleContainsOrderByDateDesc(String searchWord) {
        return postRepository.findAllByTitleContainsOrderByDateDesc(searchWord);
    }

    @Override
    public List<Post> findAllByUserOrTitleAllIgnoreCaseContainsOrderByDateDesc(User searchWord, String title) {
        return postRepository.findAllByUserOrTitleAllIgnoreCaseContainsOrderByDateDesc(searchWord,title);
    }


}