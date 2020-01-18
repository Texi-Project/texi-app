package com.texi.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostData implements Serializable {
    Long postId;
    String imageName;
    byte[] image;
    String videoName;
    byte[] video;
    Boolean notify;

    public PostData(Long postId, Boolean notify) {
        this.postId = postId;
        this.notify = notify;
    }
}
