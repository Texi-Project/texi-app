package com.texi.app.like.repository;

import com.texi.app.domain.Comment;
import com.texi.app.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {



}
