package com.texi.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@ControllerAdvice
public class ErrorHandling {

    @ExceptionHandler({IOException.class, Exception.class})
    public RedirectView handle(Exception e) {
        // fail silently for all exceptions in Controllers
        System.out.println(e.getMessage());
        return new RedirectView("/timeline");
    }
}
