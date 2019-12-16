package com.texi.app.aop;

import com.texi.app.domain.Flag;
import com.texi.app.domain.Post;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import com.texi.app.flag.FlagRepository;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RabbitListener(queues = {"texiPostUnhealthy"})
public class FilteringEngine {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserServices userServices;

    @Autowired
    private PostService postService;

    @Autowired
    private FlagRepository flagRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "texiPostUnhealthy";
    private static final String ROUTING_KEY = "texiPostUnhealthy";
    private static int FLAG_COUNT = 9;

    private List<String> badWords;

    public Post filterPost(Post post, User user) {
        List<String> words = Arrays.asList(post.getDescription().split(" "));
        List<String> badWordsFound = words.stream().filter(w -> badWords.contains(w.toLowerCase()) ).collect(Collectors.toList());

        if(badWordsFound.isEmpty()){
            return post;
        }

        Flag flag = new Flag();
        flag.setNoOfWords(badWordsFound.size());
        flag.setPost(post);
        flag.setUser(user);
        post.setStatus(Status.DEACTIVATED);
        post.setFlag(flag);

        logger.info("post:flagged "+user.getUsername()+" "+user.getId()+" UNHEALTHY");
        List<Object> payload = Arrays.asList(user.getUsername(), post.getDescription(), post.getDate().toString());
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, payload);
        return post;
    }


    @RabbitHandler
    public void handleFlaggedMessages(List<Object> message)
    {
        logger.info(" receive message [" + message.toString() + "] ");
        String username =  message.get(0).toString();
        User user = userServices.findByUsername(username);
        int count = flagRepository.countAllByUser(user);
        logger.info("count = "+count);
        if(count>FLAG_COUNT){
            sendEmail();
            userServices.updateStatus(user,Status.DEACTIVATED);
        }
    }

    private void sendEmail(){

    }

    // @TODO get these words from a file | database
    public FilteringEngine(){
        String[] words = {"evil", "fuck", "shit", "dick head", "asshole", "bastard", "bitch", "damn", "bollocks", "bugger",
                "bloody", "hell"};
        badWords = Arrays.asList(words);
    }
}
