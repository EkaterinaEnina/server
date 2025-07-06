package com.example.models.exist;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExistResponse {
    @JsonProperty("exist")
    private boolean exist;

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean getExistStatus() {
        return exist;
    }
}