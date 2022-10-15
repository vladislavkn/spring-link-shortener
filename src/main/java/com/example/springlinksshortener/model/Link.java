package com.example.springlinksshortener.model;

import com.example.springlinksshortener.dto.CreateLinkDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @JsonIgnore
    private long id;
    private String originalLink;
    private String hash;
    private long ttl;
    @JsonIgnore
    private long dateCreated;

    public Link() {}

    public Link(String originalLink, long ttl) {
        this.originalLink = originalLink;
        this.ttl = ttl;
        dateCreated = new Date().getTime();
        hash = Integer.toString(originalLink.hashCode());
    }

    public static Link fromDTO(CreateLinkDTO createLinkDTO) {
       return new Link(createLinkDTO.getOriginalLink(), createLinkDTO.getTtl());
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isExpired() {
        if(ttl == 0) return false;
        return new Date().getTime() - dateCreated > ttl;
    }

    @Override
    public String toString() {
        String template = "Link{id=%d originalLink=%s ttl=%d hash=%s dateCreated=%d}";
        return String.format(template, id, originalLink, ttl, hash, dateCreated);
    }
}
