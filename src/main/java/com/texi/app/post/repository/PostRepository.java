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
    List<Post> findAll();


    public List<Post> findAllByDescriptionOrByTitleOrByUser_UsernameOrUser_LastNameOrUser_FirstNameAllIgnoreCaseContainsOrderByDateDesc(String description,String title,String username,String lName,String fName);
    public List<Post> findFollowingsPost(Integer id,String title,String descrtiption,String fName,String lName,String uName,String fLName,String FfName,String fUName );
}
