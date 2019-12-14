package com.texi.app.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    Cloudinary getCloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "deb8hsadi",
                "api_key", "785365838447324",
                "api_secret", "Q4lFp1K2bxUDuz3RDQJovND6vAk"));
    }
}
