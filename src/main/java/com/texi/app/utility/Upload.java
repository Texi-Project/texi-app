package com.texi.app.utility;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Component
public class Upload {
    Cloudinary cloudinary;

    @Value("${upload.dir}")
    private String uploads;

    public String upload(MultipartFile multipartFile) {
        String url = String.format("%s%s-%s",
                uploads, Instant.now().getEpochSecond(), multipartFile.getOriginalFilename()); // placeholder
        try {
            byte[] bytes = multipartFile.getBytes();
            Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());
            url = (String) uploadResult.get("url");
            System.out.printf("%s uploaded successfully to %s\n", multipartFile.getOriginalFilename(), url);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        return url;
    }
}
