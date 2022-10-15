package com.example.springlinksshortener.service;

import com.example.springlinksshortener.dto.CreateLinkDTO;
import com.example.springlinksshortener.exception.LinkExpiredException;
import com.example.springlinksshortener.exception.LinkNotFoundException;
import com.example.springlinksshortener.model.Link;
import com.example.springlinksshortener.repository.LinkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LinkService {
    private LinkRepository repository;

    @Autowired
    public LinkService(LinkRepository repository) {
        this.repository = repository;
    }

    public Link getLinkByHash(String hash) {
        Link link = repository.findByHash(hash);
        if (link == null) throw new LinkNotFoundException(hash);

        if(link.isExpired()) {
            repository.deleteById(link.getId());
            throw new LinkExpiredException(hash);
        }

        return link;
    }

    public Link createLinkFromDTO(CreateLinkDTO createLinkDTO) {
        return repository.save(Link.fromDTO(createLinkDTO));
    }

    public void deleteLinkByHash(String hash) {
        Link link = repository.findByHash(hash);
        if (link == null) throw new LinkNotFoundException(hash);
        repository.deleteById(link.getId());
    }

    public int deleteExpiredLinks() {
        AtomicInteger linksDeleted = new AtomicInteger();
        repository.findAll().stream().forEach(link -> {
            if(link.isExpired()) {
                repository.deleteById(link.getId());
                linksDeleted.addAndGet(1);
            }
        });

        return linksDeleted.get();
    }
}
