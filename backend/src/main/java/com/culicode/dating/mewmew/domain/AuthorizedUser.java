package com.culicode.dating.mewmew.domain;

public class AuthorizedUser {
    private User user;
    private String message;
    private String token;

    public AuthorizedUser(User user, String token, String message) {
        this.user = user;
        this.token = token;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
