package com.texi.app.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.texi.app.domain.PostData;
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
    public void consume(String message) throws InterruptedException {
        logger.info("received {} from {} queue", message, queue);
        try {
            PostData payload = new ObjectMapper().readValue(message, PostData.class);
            postService.handlePostProcessing(payload);
        } catch (JsonProcessingException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
