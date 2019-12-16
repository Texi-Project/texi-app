package com.texi.app.utility;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Component
public class Upload {

    private static final Logger logger = LoggerFactory.getLogger(Upload.class);

    Cloudinary cloudinary;

    @Value("${upload.dir}")
    private String uploads;

    @Autowired
    public Upload(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String upload(MultipartFile multipartFile) {
        String url = String.format("%s%s-%s",
                uploads, Instant.now().getEpochSecond(), multipartFile.getOriginalFilename()); // placeholder
        try {
            byte[] bytes = multipartFile.getBytes();
            Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());
            url = (String) uploadResult.get("url");
            logger.info("{} uploaded successfully to {}\n", multipartFile.getOriginalFilename(), url);
        } catch (IOException e) {
            logger.error(e.getStackTrace().toString());
        }
        return url;
    }

    public String upload(String fileName, byte[] bytes) {
        String url = String.format("%s%s-%s", uploads, Instant.now().getEpochSecond(), fileName); // placeholder
        try {
            Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());
            url = (String) uploadResult.get("url");
            logger.info("{} uploaded successfully to {}\n", fileName, url);
        } catch (IOException e) {
            logger.error(e.getStackTrace().toString());
        }
        return url;
    }
}
