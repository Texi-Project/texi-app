package com.texi.app.post.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.Text;
import com.texi.app.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SearchPostService {
    List<Post> findAllByUser(User user);
    //mulie
    public Post findByTitle(String title);
    List<Post> findAllByDescriptionOrderByDateAsc(String lastname);
    public List<Post> findAllByDescriptionContains(String searchWord);
    public List<Post> findAllByTitleContainsOrderByDateDesc(String searchWord);
    public List<Post> findAllByUserOrTitleAllIgnoreCaseContainsOrderByDateDesc(User searchWord,String title);

}
