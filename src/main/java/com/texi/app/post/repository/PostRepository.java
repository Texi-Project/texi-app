package com.texi.app.post.repository;

import com.texi.app.domain.Advert;
import com.texi.app.domain.Post;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAll();

    List<Post> findAllByUser(User user);

    @Query("from Post p where p.status =:status")
    List<Post> findByStatus(Status status);

    @Modifying
    @Query("update Post p set p.status = 'ACTIVE' where p.id = :id")
    void enablePost(Long id);
    // should load the latest posts from followers' posts and own posts order by created Date
    @Query(nativeQuery = true,
//            value = "from Post p join p.user u join u.following f where u.id = :id " +
//                    "or exists (select u from User u join u.following f where f.id = :id) order by p.date desc")
            value = "select * from post po, photo ph where po.id = ph.post_id and po.user_id = :id or po.user_id in " +
                    "(select following_id from user_following where user_id = :id) order by po.date desc")
    List<Post> getPostsForUser(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from post p where p.dtype = 'Advert'")
    List<Advert> findAllAdverts();

}
