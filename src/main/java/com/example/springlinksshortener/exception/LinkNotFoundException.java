package com.example.springlinksshortener.exception;

public class LinkNotFoundException extends RuntimeException{
    public LinkNotFoundException(String hash) {
        super("Link has not been found " + hash);
    }
}
