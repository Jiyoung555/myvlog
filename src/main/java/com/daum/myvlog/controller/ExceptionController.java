package com.daum.myvlog.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.daum.myvlog.api")
public class ExceptionController{
    @ExceptionHandler(IllegalArgumentException.class)
    public String notFound(Exception exception, Model model){
        model.addAttribute("exception", exception);
        return "errors/404-error";
    }
}
