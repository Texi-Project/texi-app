package com.texi.app.comment.controller;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class CommentResponse {
    private  String msg;
    private Long commentPostUserId;
    private String commentText;
    private String commentPostUserName;
    private LocalDateTime commentDate;
}
