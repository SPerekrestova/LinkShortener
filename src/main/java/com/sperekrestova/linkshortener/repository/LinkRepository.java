package com.sperekrestova.linkshortener.repository;

import com.sperekrestova.linkshortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByLinkCode(String linkCode);
}