package com.sperekrestova.linkshortener.service;

public interface LinkService {
    String createLink(String originalLink);

    String getOriginalLink(String shortLink);
}
