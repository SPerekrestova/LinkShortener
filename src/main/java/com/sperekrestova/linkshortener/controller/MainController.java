package com.sperekrestova.linkshortener.controller;

import com.sperekrestova.linkshortener.service.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class MainController {
    private LinkService linkService;

    @PostMapping("/create")
    public ResponseEntity<String> createLink(@RequestBody String originalLink) {
        return ResponseEntity.ok(linkService.createLink(originalLink));
    }

    @GetMapping("/{linkCode}")
    public ResponseEntity<Void> getFullLink(@PathVariable String linkCode) {
        String originalLink = linkService.getOriginalLink(linkCode);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalLink)).build();
    }
}
