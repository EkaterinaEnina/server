package com.example.utils;

import com.example.models.user.login.AuthRequest;
import com.example.models.user.login.SuccessLogin;

public class AuthHelper {

    public static String loginAndGetToken() {
        String email = Secrets.getEmail();
        AuthRequest authRequest = new AuthRequest(email, Secrets.getPassword());
        SuccessLogin authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            200, 
            SuccessLogin.class
        );
        return authResponse.getToken();
    }
}