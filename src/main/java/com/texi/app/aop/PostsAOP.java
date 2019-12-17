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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Aspect
@Configuration
public class PostsAOP {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FilteringEngine filteringEngine;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    //    private static final String EXCHANGE = "texi.post.unhelthy";
//    private static final String ROUTING_KEY = "texi.post.unhealthy";
    private static final String EXCHANGE = "duncan";
    private static final String ROUTING_KEY = "msg";

    @Around("execution(* com.texi.app.post.service.PostService.save(..))")
    public Object filterPosts(ProceedingJoinPoint pjp){
        String m = pjp.getSignature().getName();
        logger.info("Post filter before - {}", m);

        Object[] objects = pjp.getArgs();
        User user = (User) objects[1];
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
        logger.info("User Service method "+joinPoint.getSignature().getName());
//        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,joinPoint.getSignature().getName());
    }

}
