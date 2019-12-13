package com.texi.app.post.repository;

import com.texi.app.domain.Post;
import com.texi.app.domain.Text;
import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
 //   List<Post> findAll();


    List<Post> findAllByUser(User user);
    //mulie
    public Post findByTitle(String title);
    List<Post> findAllByDescriptionOrderByDateAsc(String lastname);
    public List<Post> findAllByDescriptionContains(String searchWord);
    public List<Post> findAllByTitleContainsOrderByDateDesc(String searchWord);
    public List<Post> findAllByUserOrTitleAllIgnoreCaseContainsOrderByDateDesc(User searchWord,String title);

}
