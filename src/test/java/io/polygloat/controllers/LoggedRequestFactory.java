package io.polygloat.controllers;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class LoggedRequestFactory {

    static String token;

    static void init(String token) {
        LoggedRequestFactory.token = token;
    }

    public static MockHttpServletRequestBuilder loggedGet(String url) {
        return addToken(MockMvcRequestBuilders.get(url));
    }

    public static MockHttpServletRequestBuilder loggedPost(String url) {
        return addToken(MockMvcRequestBuilders.post(url));
    }

    public static MockHttpServletRequestBuilder loggedDelete(String url) {
        return addToken(MockMvcRequestBuilders.delete(url));
    }

    public static MockHttpServletRequestBuilder addToken(MockHttpServletRequestBuilder builder) {
        return builder.header("Authorization", String.format("Bearer %s", token));
    }
}