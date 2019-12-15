package com.texi.app.aop;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class PostsAOP {

    @Autowired
    FilteringEngine filteringEngine;

    @Around("execution(* *.PostServiceImpl.save(..))")
    public Object filterPosts(ProceedingJoinPoint pjp){
        String m = pjp.getSignature().getName();
        Object[] objects = pjp.getArgs();
        System.out.println("Before " + m);
        User user = (User) objects[1];
//        @todo send here to a queue from first response to the user.
        Post filteredPost = filteringEngine.filterPost((Post) objects[0],user);
        objects[0] = filteredPost;

        Object ret = null;
        try {
            ret = pjp.proceed(objects);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("After " + m + " returned " + ret);
        return ret;
    }

}
