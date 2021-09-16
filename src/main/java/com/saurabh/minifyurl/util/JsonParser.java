package com.saurabh.minifyurl.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonParser {

    private final String host;

    public JsonParser(@Value("${api.origin.url}") String host) {
        this.host = host;
    }

    public Map<String, String> parse(String longUrl, String shortUrl) {
        HashMap<String, String> map = new HashMap<>();
        map.put("longUrl", longUrl);
        map.put("shortUrl", host + shortUrl);
        return map;
    }
}
