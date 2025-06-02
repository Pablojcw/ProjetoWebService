package com.app.api.controller.URL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class URLController {

    public static String decodeParameter(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}

