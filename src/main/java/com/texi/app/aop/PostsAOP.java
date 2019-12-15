package com.texi.app.aop;

import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Aspect
@Configuration
public class PostsAOP {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FilteringEngine filteringEngine;

    @Around("execution(* com.texi.app.post.service.PostService.save(..))")
    public Object filterPosts(ProceedingJoinPoint pjp){
        String m = pjp.getSignature().getName();

        logger.info("Post filter before - {}", m);
        Object[] objects = pjp.getArgs();
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
        logger.info("Post filter after - {}", m);
        return ret;
    }

    @Before("execution(* com.texi.app.user.service.UserServices.*(..))")
    public void before(JoinPoint joinPoint) {
        //Advice
        logger.info("User Service method "+joinPoint.getSignature().getName());
    }

}
