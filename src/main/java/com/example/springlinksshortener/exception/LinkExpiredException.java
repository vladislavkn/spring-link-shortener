package com.example.springlinksshortener.exception;

public class LinkExpiredException extends RuntimeException{
    public LinkExpiredException(String hash) {
        super("Link has been expired " + hash);
    }
}
