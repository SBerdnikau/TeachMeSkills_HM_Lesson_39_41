package com.teachmeskills.market_32.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = AgeException.class)
    public ModelAndView securityExceptionHandler(AgeException error){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", error.getMessage());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.setViewName("innerError");
        return modelAndView;
    }

    public ModelAndView allExceptionHandler(Exception error){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", error.getMessage());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.setViewName("innerError");
        return modelAndView;
    }

}
