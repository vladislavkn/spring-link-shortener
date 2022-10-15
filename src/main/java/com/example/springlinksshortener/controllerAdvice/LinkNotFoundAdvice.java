package com.example.springlinksshortener.controllerAdvice;

import com.example.springlinksshortener.exception.LinkNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LinkNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String linkNotFoundHandler(LinkNotFoundException exception) {
        return exception.getMessage();
    }
}
