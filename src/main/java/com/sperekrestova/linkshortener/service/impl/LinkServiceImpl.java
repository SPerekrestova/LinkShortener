package com.sperekrestova.linkshortener.service.impl;

import com.sperekrestova.linkshortener.model.Link;
import com.sperekrestova.linkshortener.repository.LinkRepository;
import com.sperekrestova.linkshortener.service.LinkService;
import com.sperekrestova.linkshortener.util.RandomUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LinkServiceImpl implements LinkService {

    private LinkRepository linkRepository;


    @Override
    @Transactional
    public String createLink(String originalLink) {
        // generate random UUID
        String unique = RandomUtil.unique();
        // check whether we don't have it in DB
        while (linkRepository.findByLinkCode(unique).isPresent()) {
            unique = RandomUtil.unique();
        }
        Link link = Link.builder().linkCode(unique).originalLink(originalLink).build();
        linkRepository.save(link);
        // build link
        return "https://linkshortener.com/" + unique; //TODO fix me
    }

    @Override
    public String getOriginalLink(String linkCode) {
        Optional<Link> optionalLink = linkRepository.findByLinkCode(linkCode);
        if (optionalLink.isPresent()) {
            return optionalLink.get().getOriginalLink();
        } else {
            // TODO if no link found should redirect to our special 404 page
            return "https://linkshortener.com/nothing";
        }
    }
}
