package com.saurabh.minifyurl.controllers;

import com.saurabh.minifyurl.error.CustomErrorType;
import com.saurabh.minifyurl.model.Url;
import com.saurabh.minifyurl.service.UrlService;
import com.saurabh.minifyurl.util.JsonParser;
import com.saurabh.minifyurl.util.VerifyUrl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UrlController {

    private final UrlService urlService;
    private final JsonParser parser;

    public UrlController(UrlService urlService, JsonParser parser) {
        this.urlService = urlService;
        this.parser = parser;
    }

    @CrossOrigin
    @PostMapping(value = "/api", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addUrl(@RequestParam("url") String longUrl) {
        if (VerifyUrl.isValid(longUrl)) {
            Url savedUrl = urlService.insert(longUrl);
            return new ResponseEntity<>(parser.parse(savedUrl.getLongUrl(), savedUrl.getShortUrl()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Url is not valid"), HttpStatus.BAD_REQUEST);
    }
}
