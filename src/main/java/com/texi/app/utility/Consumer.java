package com.texi.app.utility;

import com.texi.app.domain.Payload;
import com.texi.app.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    PostService postService;

    @Value("${amqp.queues.posts}")
    private String queue;

    @RabbitListener(queues = {"#{postsQueue.name}"})
    public void consume(Payload payload) throws InterruptedException {
        logger.info("received {} from {} queue", payload, queue);
        postService.handlePostProcessing(payload);
    }
}
