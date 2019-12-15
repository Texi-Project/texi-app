package com.texi.app.aop;

import com.texi.app.domain.Flag;
import com.texi.app.domain.Post;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import com.texi.app.flag.FlagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilteringEngine {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("post:flagged "+user.getUsername()+" "+post.getId()+" UNHEALTHY");
//        @TODO notify by sending to a queue
        return post;
    }

    // @todo get these words from a file | database
    public FilteringEngine(){
        String[] words = {"evil", "fuck", "shit", "dick head", "asshole", "bastard", "bitch", "damn", "bollocks", "bugger",
        "bloody", "hell"};
        badWords = Arrays.asList(words);
    }

}
