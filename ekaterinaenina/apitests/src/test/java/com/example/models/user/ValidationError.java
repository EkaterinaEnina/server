package com.example.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ValidationError {

    @JsonProperty("type")
    private String type;

    @JsonProperty("on")
    private String on;

    @JsonProperty("found")
    private Map<String, String> found;

    public String getType() {
        return type;
    }

    public String getOn() {
        return on;
    }

    public Map<String, String> getFound() {
        return found;
    }

    public String getFoundField(String fieldName) {
        return found != null ? found.get(fieldName) : null;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "type='" + type + '\'' +
                ", on='" + on + '\'' +
                ", found=" + found +
                '}';
    }
}
