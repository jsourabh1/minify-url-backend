package com.saurabh.minifyurl.util;

import java.util.regex.Pattern;

public class VerifyUrl {

    public static boolean isValid(String longUrl) {
        return Pattern.compile("^htt(p|ps)://www[.].*").matcher(longUrl).matches();
    }
}
