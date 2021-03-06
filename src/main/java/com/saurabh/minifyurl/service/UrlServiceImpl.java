package com.saurabh.minifyurl.service;

import com.saurabh.minifyurl.model.Url;
import com.saurabh.minifyurl.repository.UrlRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url insert(String longUrl) {
        String shortUrl = DigestUtils.md5Hex(longUrl).substring(0, 10);
        Url url = urlRepository.findByShortUrl(shortUrl);
        if (url == null) {
            Url saveUrl = new Url();
            saveUrl.setLongUrl(longUrl);
            saveUrl.setShortUrl(shortUrl);
            return urlRepository.insert(saveUrl);
        }
        return url;
    }

    @Override
    public Url findByShortUrl(String shortURL) {
        return urlRepository.findByShortUrl(shortURL);
    }
}
