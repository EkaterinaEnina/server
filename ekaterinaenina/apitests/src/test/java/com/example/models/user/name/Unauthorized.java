package com.example.models.user.name;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Unauthorized {
    @JsonProperty("message")
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
