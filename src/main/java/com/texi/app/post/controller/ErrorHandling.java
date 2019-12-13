package com.texi.app.post.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ErrorHandling {

    @ExceptionHandler({IOException.class, Exception.class})
    public String handle(Exception e) {
        // fail silently for all exceptions in Controllers
        System.out.println(e.getMessage());
        return "dashboard";
    }
}
