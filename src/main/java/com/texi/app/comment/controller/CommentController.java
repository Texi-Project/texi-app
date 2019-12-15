package com.texi.app.comment.controller;

import com.texi.app.comment.service.CommentService;
import com.texi.app.domain.Comment;
import com.texi.app.domain.Post;
import com.texi.app.domain.User;
import com.texi.app.post.service.PostService;
import com.texi.app.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")//to be modified when needed
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserServices userServices;

    @PostMapping("/add")// to be modified to implement PRG pattern
    public  ResponseEntity<?> addComment(@RequestBody CommentJson commentJson, Errors errors, Principal principal){
        CommentResponse responsejson=new CommentResponse();
        if(errors.hasErrors()){
            responsejson.setMsg(errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(responsejson);
        }
        responsejson.setPostId(commentJson.getPostId());
        responsejson.setCommentText(commentJson.getCommentText());
        responsejson.setMsg("Success");
        Post post=postService.findById(commentJson.getPostId());
        User user=userServices.findByUsername(principal.getName());
        Comment commentObj=new Comment();
        commentObj.setText(commentJson.getCommentText());
        commentObj.setPost(post);
        commentObj.setUser(user);
        commentService.save(commentObj);
        return ResponseEntity.ok(responsejson);
    }
//    @PostMapping("/add")// to be modified to implement PRG pattern
//    public void addComment(@RequestParam("pcomment") String pcomment, @RequestParam("postId") Long postId, Principal principal){
//        Post post=postService.findById(postId);
//        User user=userServices.findByUsername(principal.getName());
//        Comment commentObj=new Comment();
//        commentObj.setText(pcomment);
//        commentObj.setPost(post);
//        commentObj.setUser(user);
//        commentService.save(commentObj);
//    }
    @PostMapping("/delete/{id}")// to be modified to implement PRG pattern
    public boolean delete(@PathVariable Long id){
        return commentService.deleteById(id);
    }


}
