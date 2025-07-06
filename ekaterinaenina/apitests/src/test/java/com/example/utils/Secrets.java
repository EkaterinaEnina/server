package com.example.utils;

import com.example.secret.SecretsData;

public class Secrets {
    public static String getEmail() {
        return SecretsData.EMAIL;
    }

    public static String getPassword() {
        return SecretsData.PASSWORD;
    }
}