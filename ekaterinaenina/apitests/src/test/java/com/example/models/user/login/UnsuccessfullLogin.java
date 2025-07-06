package com.example.models.user.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnsuccessfullLogin {
    @JsonProperty("fields")
    private Fields fields;

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Fields getFields() {
        return fields;
    }

    public static class Fields {
        @JsonProperty("password")
        private String password;

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }
}
