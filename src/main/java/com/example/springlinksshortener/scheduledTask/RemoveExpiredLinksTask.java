package com.example.springlinksshortener.scheduledTask;

import com.example.springlinksshortener.service.LinkService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveExpiredLinksTask {

    private LinkService linkService;
    private static final Logger log = LoggerFactory.getLogger(RemoveExpiredLinksTask.class);

    @Autowired
    public RemoveExpiredLinksTask(LinkService linkService) {
        this.linkService = linkService;
    }

    @Scheduled(fixedRate = 600000)
    public void removeExpiredLinks() {
        log.info("Start RemoveExpiredLinksTask");
        int linksDeleted = linkService.deleteExpiredLinks();
        log.info("Finish RemoveExpiredLinksTask; Deleted " + linksDeleted + " links.");
    }
}
