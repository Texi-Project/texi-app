package com.texi.app.comment.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponse {
    String msg;
    private Long postId;
    private String commentText;
}
