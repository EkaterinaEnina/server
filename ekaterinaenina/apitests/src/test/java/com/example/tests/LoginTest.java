package com.example.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.utils.Secrets;
import com.example.models.user.ValidationError;
import com.example.models.user.login.AuthRequest;
import com.example.models.user.login.SuccessLogin;
import com.example.models.user.login.UnsuccessfullLogin;
import com.example.utils.HttpClientHelper;
import com.example.utils.RandomUtils;

public class LoginTest {
    @Test
    public void testLoginSuccess() {
        String email = Secrets.getEmail();
        AuthRequest authRequest = new AuthRequest(email, Secrets.getPassword());
        SuccessLogin authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            200, 
            SuccessLogin.class
        );

        assertEquals(email, authResponse.getUser().getEmail());
    }

    @Test
    public void testLoginInapproppriatePassword() {
        AuthRequest authRequest = new AuthRequest(Secrets.getEmail(), RandomUtils.randomPassword());
        UnsuccessfullLogin authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            UnsuccessfullLogin.class
        );

        assertEquals("Неправильный логин или пароль", authResponse.getFields().getPassword());
    }

    @Test
    public void testLoginInapproppriateLogin() {
        AuthRequest authRequest = new AuthRequest(RandomUtils.randomFalseEmail(), Secrets.getPassword());
        UnsuccessfullLogin authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            UnsuccessfullLogin.class
        );

        assertEquals("Неправильный логин или пароль", authResponse.getFields().getPassword());
    }

    @Test
    public void testLoginMissingEmail() {
        String password = RandomUtils.randomPassword();
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword(password);
        ValidationError authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            ValidationError.class
        );

        assertEquals(password, authResponse.getFoundField("password"));
    }


    @Test
    public void testLoginMissingPassword() {
        String email = RandomUtils.randomEmail();
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        ValidationError authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            ValidationError.class
        );
        assertEquals("validation", authResponse.getType());
        assertEquals("body", authResponse.getOn());
        assertEquals(email, authResponse.getFoundField("email"));
    }

    @Test
    public void testLoginMissingData() {
        AuthRequest authRequest = new AuthRequest();
        ValidationError authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            ValidationError.class
        );
        assertEquals("validation", authResponse.getType());
        assertEquals("body", authResponse.getOn());
        assertNull(authResponse.getFoundField("email"));
        assertNull(authResponse.getFoundField("password"));
    }

    @Test
    public void testLoginRussianEmail() {
        String email = RandomUtils.randomRussianEmail();
        String password = RandomUtils.randomPassword();
        AuthRequest authRequest = new AuthRequest(email, password);
        ValidationError authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            ValidationError.class
        );

        assertEquals("validation", authResponse.getType());
        assertEquals("body", authResponse.getOn());
        assertEquals(email, authResponse.getFoundField("email"));
        assertEquals(password, authResponse.getFoundField("password"));
    }
    
    @Test
    public void testLoginSpaceBeforeEmail() {
        String email = RandomUtils.randomEmailSpaceBefore();
        String password = RandomUtils.randomPassword();
        AuthRequest authRequest = new AuthRequest(email, password);
        ValidationError authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            ValidationError.class
        );

        assertEquals("validation", authResponse.getType());
        assertEquals("body", authResponse.getOn());
        assertEquals(email, authResponse.getFoundField("email"));
        assertEquals(password, authResponse.getFoundField("password"));
    }

    @Test
    public void testLoginSpaceAfterEmail() {
        String email = RandomUtils.randomEmailSpaceAfter();
        String password = RandomUtils.randomPassword();
        AuthRequest authRequest = new AuthRequest(email, password);
        ValidationError authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            ValidationError.class
        );

        assertEquals("validation", authResponse.getType());
        assertEquals("body", authResponse.getOn());
        assertEquals(email, authResponse.getFoundField("email"));
        assertEquals(password, authResponse.getFoundField("password"));
    }

    @Test
    public void testLoginEmailWithoutSpetialSymbolA() {
        String email = RandomUtils.randomText();
        String password = RandomUtils.randomPassword();
        AuthRequest authRequest = new AuthRequest(email, password);
        ValidationError authResponse = HttpClientHelper.postJsonAndParse(
            "/auth/login", 
            authRequest,
            422, 
            ValidationError.class
        );

        assertEquals("validation", authResponse.getType());
        assertEquals("body", authResponse.getOn());
        assertEquals(email, authResponse.getFoundField("email"));
        assertEquals(password, authResponse.getFoundField("password"));
    }
}