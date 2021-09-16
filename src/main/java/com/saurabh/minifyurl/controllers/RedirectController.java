package com.saurabh.minifyurl.controllers;

import com.saurabh.minifyurl.error.CustomErrorType;
import com.saurabh.minifyurl.model.Url;
import com.saurabh.minifyurl.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;


@Controller
@RequestMapping("/")
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrl) throws URISyntaxException {
        Url url = urlService.findByShortUrl(shortUrl);
        if (url != null) {
            URI redirectUrl = new URI(url.getLongUrl());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUrl);
            return new ResponseEntity<>(httpHeaders, HttpStatus.PERMANENT_REDIRECT);
        }
        return new ResponseEntity<>(new CustomErrorType("Url not found"), HttpStatus.BAD_REQUEST);
    }
}
