package com.texi.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostData implements Serializable {
    Post post;
    MultipartFile image;
    MultipartFile video;
    Boolean notify;
}
