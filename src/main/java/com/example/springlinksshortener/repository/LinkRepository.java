package com.example.springlinksshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.springlinksshortener.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByHash(String hash);
}
