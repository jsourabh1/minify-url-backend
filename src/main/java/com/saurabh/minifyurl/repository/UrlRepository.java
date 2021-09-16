package com.saurabh.minifyurl.repository;

import com.saurabh.minifyurl.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {

    Url findByShortUrl(String shortUrl);
}
