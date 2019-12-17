package com.texi.app.post.service;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostService  {
   void save(Post post);

   Post findById(Long id);

    List<Post> findAll();

    void delete(Long id);

    public List<Post> findAllByDescriptionOrByTitleOrByUser_UsernameOrUser_LastNameOrUser_FirstNameAllIgnoreCaseContainsOrderByDateDesc(String description,String title,String username,String lName,String fName);
    public List<Post> findFollowingsPost(Integer id,String title,String descrtiption,String fName,String lName,String uName,String fLName,String FfName,String fUName );
}
