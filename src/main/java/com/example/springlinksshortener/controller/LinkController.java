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
import com.example.springlinksshortener.model.Link;
import com.example.springlinksshortener.service.LinkService;

@RestController
@RequestMapping("/links")
public class LinkController {
    private LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Link> getLinkByHash(@PathVariable String hash) {
        Link link = linkService.getLinkByHash(hash);
        return new ResponseEntity(link, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Link> createLinkС(@RequestBody CreateLinkDTO createLinkDTO) {
        Link link = linkService.createLinkFromDTO(createLinkDTO);
        return new ResponseEntity(link, HttpStatus.CREATED);
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity deleteLinkByHash(@PathVariable String hash) {
        linkService.deleteLinkByHash(hash);
        return new ResponseEntity(HttpStatus.OK);
    }
}
