package com.example.springlinksshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlinksshortener.dto.CreateLinkDTO;
import com.example.springlinksshortener.exception.LinkExpiredException;
import com.example.springlinksshortener.exception.LinkNotFoundException;
import com.example.springlinksshortener.model.Link;
import com.example.springlinksshortener.repository.LinkRepository;

@RestController
@RequestMapping("/links")
public class LinkController {
    private LinkRepository repository;

    @Autowired
    public LinkController(LinkRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Link> getLinkByHash(@PathVariable String hash) {
        Link link = repository.findByHash(hash);
        if (link == null) throw new LinkNotFoundException(hash);

        if(link.isExpired()) {
            repository.deleteById(link.getId());
            throw new LinkExpiredException(hash);
        }

        return new ResponseEntity(link, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Link> createLinkС(@RequestBody CreateLinkDTO createLinkDTO) {
        Link link = repository.save(Link.fromDTO(createLinkDTO));
        return new ResponseEntity(link, HttpStatus.CREATED);
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity deleteLinkByHash(@PathVariable String hash) {
        Link link = repository.findByHash(hash);
        if (link == null) throw new LinkNotFoundException(hash);
        repository.deleteById(link.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}
