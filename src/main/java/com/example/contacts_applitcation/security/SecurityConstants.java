package com.example.contacts_applitcation.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 DAYS
    public static final String TOKEN_PREFIX = "Ghost ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    // min length 64 characters
    public static final String TOKEN_SECRET = "skuqwzidtxae62l8rnbj0my5xmy5ec75l337uinixnyal9idxjzxo8fjvuejoash";
}