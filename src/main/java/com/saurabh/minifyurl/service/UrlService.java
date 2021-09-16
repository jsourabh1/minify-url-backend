package com.saurabh.minifyurl.service;

import com.saurabh.minifyurl.model.Url;

public interface UrlService {

    Url insert (String longUrl);
    Url findByShortUrl(String shortURL);
}
