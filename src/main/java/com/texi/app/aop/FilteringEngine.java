package com.texi.app.aop;

import com.texi.app.domain.Flag;
import com.texi.app.domain.Post;
import com.texi.app.domain.Status;
import com.texi.app.domain.User;
import com.texi.app.flag.FlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilteringEngine {

    @Autowired
    private FlagRepository flagRepository;
//  @todo Logging
    private List<String> badWords;

    public Post filterPost(Post post, User user) {
        List<String> words = Arrays.asList(post.getDescription());
        List<String> badWordsFound = words.stream().filter(w -> badWords.contains(w)).collect(Collectors.toList());

        if(badWordsFound.isEmpty()){
            return post;
        }

        Flag flag = new Flag();
        flag.setNoOfWords(badWordsFound.size());
        flag.setPost(post);
        flag.setUser(user);
        flagRepository.save(flag);
        post.setStatus(Status.DEACTIVATED);
        return post;
    }

    // @todo get these words from a file | database
    public FilteringEngine(){
        String[] words = {"evil", "fuck", "Shit", "Dick head", "Asshole", "Bastard", "Bitch", "Damn", "Bollocks", "Bugger",
        "Bloody Hell"};
        badWords = Arrays.asList(words);
    }

}
