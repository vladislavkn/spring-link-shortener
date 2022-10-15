package com.example.springlinksshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.springlinksshortener.model.Link;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByHash(String hash);
}
