package com.example.models.user.name;

import com.example.models.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccesfullChangeName {
    @JsonProperty("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
