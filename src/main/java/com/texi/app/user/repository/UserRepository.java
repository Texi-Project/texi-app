package com.texi.app.user.repository;

import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    @Query("select f from User u join u.following f where f.id =:other and u.id = :me")
    User findFollowing(Long me, Long other);

    @Query("select u from User u where u.username = :username")
    User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from user u left join user_following f on u.id = f.user_id where u.id " +
            "not in (select h.following_id from user_following h where h.user_id = :id) AND u.id != :id")
    List<User> whoToFollow(Long id);

    @Query("from User u join u.following f where f.id = :id")
    List<User> getFollowers(Long id);

}
