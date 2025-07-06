package com.example.models.user.login;

import com.example.models.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessLogin {
    @JsonProperty("token")
    private String token;

    @JsonProperty("user")
    private User user;

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

