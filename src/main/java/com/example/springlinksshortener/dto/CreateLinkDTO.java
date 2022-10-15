package com.example.springlinksshortener.dto;

public class CreateLinkDTO {
    private String originalLink;
    private Long ttl;

    public CreateLinkDTO() {
    }

    public CreateLinkDTO(String originalLink, Long ttl) {
        this.originalLink = originalLink;
        this.ttl = ttl;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
