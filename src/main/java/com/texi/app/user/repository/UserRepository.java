package com.texi.app.user.repository;

import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    @Query("select f from User u join u.following f where f.id =:other and u.id = :me")
    User findFollowing(Long me, Long other);

    @Query("select u from User u where u.username = :username")
    User findByUsername(String username);

}
