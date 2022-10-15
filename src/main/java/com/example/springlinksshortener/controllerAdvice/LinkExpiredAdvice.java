package com.example.springlinksshortener.controllerAdvice;

import com.example.springlinksshortener.exception.LinkExpiredException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LinkExpiredAdvice {

    @ResponseBody
    @ExceptionHandler(LinkExpiredException.class)
    @ResponseStatus(HttpStatus.GONE)
    String linkExpiredHandler(LinkExpiredException exception) {
        return exception.getMessage();
    }
}
