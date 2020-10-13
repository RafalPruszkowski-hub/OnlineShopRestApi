package com.store.app.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";
    public static final String DELETE_PRODUCT_URL = "/products/**";
    public static final String GET_USERS_URL = "/users";
    public static final String CREATE_PRODUCT_URL = "/products/**";
    public static final String EDIT_PRODUCT_URL = "/products/**";
    public static final String GET_PRODUCTS_URL = "/products/**";
    public static final String GET_PRODUCT_URL = "/products";

}
