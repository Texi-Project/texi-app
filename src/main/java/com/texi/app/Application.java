package com.texi.app;

import com.texi.app.user.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new File(UserController.uploadDirectory).mkdir();
		SpringApplication.run(Application.class, args);
	}
}
