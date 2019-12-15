package com.texi.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PostData implements Serializable {
    Long postId;
    String imageName;
    byte[] image;
    String videoName;
    byte[] video;
    Boolean notify;
}
