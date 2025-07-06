package com.example.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.models.exist.ExistRequest;
import com.example.models.exist.ExistResponse;
import com.example.utils.HttpClientHelper;
import com.example.utils.RandomUtils;
import com.example.utils.Secrets;


public class ExistTest {
    
    @Test
    public void testCheckUserExist() {
        
        ExistRequest existRequest = new ExistRequest(Secrets.getEmail());
        ExistResponse existResponse = HttpClientHelper.postJsonAndParse(
            "/exist", 
            existRequest,
            200, 
            ExistResponse.class
        );
  
        assertEquals(true, existResponse.getExistStatus());
    }

    @Test
    public void testCheckUserNotExist() {

        ExistRequest existRequest = new ExistRequest(RandomUtils.randomFalseEmail());
        ExistResponse existResponse = HttpClientHelper.postJsonAndParse(
            "/exist", 
            existRequest,
            200, 
            ExistResponse.class
        );
  
        assertEquals(false, existResponse.getExistStatus());
    }
}  