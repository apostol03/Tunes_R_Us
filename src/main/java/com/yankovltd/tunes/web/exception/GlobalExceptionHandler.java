package com.yankovltd.tunes.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PageNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleOtherErrors(PageNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("cause", e.getCause());
        return modelAndView;
    }
}
