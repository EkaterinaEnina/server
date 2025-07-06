package com.example.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.models.user.ValidationError;
import com.example.models.user.name.NameRequest;
import com.example.models.user.name.SuccesfullChangeName;
import com.example.models.user.name.Unauthorized;
import com.example.utils.AuthHelper;
import com.example.utils.HttpClientHelper;
import com.example.utils.RandomUtils;

public class UserTest {

    @Test
    public void testChangeUserName() {
        String token = AuthHelper.loginAndGetToken();
        String newName = RandomUtils.randomName();
        NameRequest nameRequest = new NameRequest(newName);

        SuccesfullChangeName nameResponse = HttpClientHelper.patchJsonAndAutoraizeAndParse(
            "/user/name", 
            token,
            nameRequest,
            200, 
            SuccesfullChangeName.class
        );

        assertEquals(newName, nameResponse.getUser().getName());
    }

    @Test
    public void testChangeUserNameWithRussianSymbols() {
        String token = AuthHelper.loginAndGetToken();
        String newName = RandomUtils.randomRussianName();
        NameRequest nameRequest = new NameRequest(newName);

        SuccesfullChangeName nameResponse = HttpClientHelper.patchJsonAndAutoraizeAndParse(
            "/user/name", 
            token,
            nameRequest,
            200, 
            SuccesfullChangeName.class
        );

        assertEquals(newName, nameResponse.getUser().getName());
    }

    @Test
    public void testChangeUserNameWithSpetialSymbols() {
        String token = AuthHelper.loginAndGetToken();
        String newName = RandomUtils.randomNameWithSpetialSymbols();
        NameRequest nameRequest = new NameRequest(newName);

        SuccesfullChangeName nameResponse = HttpClientHelper.patchJsonAndAutoraizeAndParse(
            "/user/name", 
            token,
            nameRequest,
            200, 
            SuccesfullChangeName.class
        );

        assertEquals(newName, nameResponse.getUser().getName());
    }

    @Test
    public void testChangeUserName51Symbols() {
        String token = AuthHelper.loginAndGetToken();
        String newName = RandomUtils.random51Symbol();
        NameRequest nameRequest = new NameRequest(newName);

        ValidationError nameResponse = HttpClientHelper.patchJsonAndAutoraizeAndParse(
            "/user/name", 
            token,
            nameRequest,
            422, 
            ValidationError.class
        );
        assertEquals("validation", nameResponse.getType());
        assertEquals("body", nameResponse.getOn());
        assertEquals(newName, nameResponse.getFoundField("name"));   
    }

    @Test
    public void testChangeUserName0Symbols() {
        String token = AuthHelper.loginAndGetToken();
        String newName = "";
        NameRequest nameRequest = new NameRequest(newName);

        ValidationError nameResponse = HttpClientHelper.patchJsonAndAutoraizeAndParse(
            "/user/name", 
            token,
            nameRequest,
            422, 
            ValidationError.class
        );
        assertEquals("validation", nameResponse.getType());
        assertEquals("body", nameResponse.getOn());
        assertEquals(newName, nameResponse.getFoundField("name"));   
    }

    @Test
    public void testChangeUserMissingNameSymbols() {
        String token = AuthHelper.loginAndGetToken();
        NameRequest nameRequest = new NameRequest();

        ValidationError nameResponse = HttpClientHelper.patchJsonAndAutoraizeAndParse(
            "/user/name", 
            token,
            nameRequest,
            422, 
            ValidationError.class
        );
        assertEquals("validation", nameResponse.getType());
        assertEquals("body", nameResponse.getOn());
        assertNull(nameResponse.getFoundField("name")); 
    }

    @Test
    public void testChangeUserNameInvalidToken() {
        String token = RandomUtils.randomText();
        String newName = RandomUtils.randomName();
        NameRequest nameRequest = new NameRequest(newName);

        Unauthorized nameResponse = HttpClientHelper.patchJsonAndAutoraizeAndParse(
            "/user/name", 
            token,
            nameRequest,
            401, 
            Unauthorized.class
        );

        assertEquals("Invalid token", nameResponse.getMessage());
    }

    @Test
    public void testChangeUserNameMissingToken() {
        String newName = RandomUtils.randomName();
        NameRequest nameRequest = new NameRequest(newName);

        Unauthorized nameResponse = HttpClientHelper.patchJsonAndParse(
            "/user/name", 
            nameRequest,
            401, 
            Unauthorized.class
        );

        assertEquals("Authorization header required", nameResponse.getMessage());
    }
}