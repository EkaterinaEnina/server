package com.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HttpClientHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T postJsonAndParse(String path, Object requestBody, int expectedStatusCode, Class<T> responseType) {
        try {
            String json = objectMapper.writeValueAsString(requestBody);

            Response response = given()
                .baseUri(Config.getBaseUrl())
                .contentType("application/json")
                .body(json)
                .when()
                .post(path);

            int actualStatus = response.getStatusCode();
            if (actualStatus != expectedStatusCode) {
                throw new RuntimeException("Ожидался статус " + expectedStatusCode + ", а получен " + actualStatus +
                        ": " + response.getBody().asString());
            }

            return objectMapper.readValue(response.getBody().asString(), responseType);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при POST запросе на " + path, e);
        }
    }

    public static <T> T patchJsonAndAutoraizeAndParse(String path, String token, Object requestBody, int expectedStatusCode, Class<T> responseType) {
        try {
            String json = objectMapper.writeValueAsString(requestBody);

            Response response = given()
                .baseUri(Config.getBaseUrl())
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(json)
                .when()
                .patch(path);

            int actualStatus = response.getStatusCode();
            if (actualStatus != expectedStatusCode) {
                throw new RuntimeException("Ожидался статус " + expectedStatusCode + ", а получен " + actualStatus +
                        ": " + response.getBody().asString());
            }

            return objectMapper.readValue(response.getBody().asString(), responseType);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при PATCH запросе на " + path, e);
        }
    }

    public static <T> T patchJsonAndParse(String path, Object requestBody, int expectedStatusCode, Class<T> responseType) {
        try {
            String json = objectMapper.writeValueAsString(requestBody);

            Response response = given()
                .baseUri(Config.getBaseUrl())
                .contentType("application/json")
                .body(json)
                .when()
                .patch(path);

            int actualStatus = response.getStatusCode();
            if (actualStatus != expectedStatusCode) {
                throw new RuntimeException("Ожидался статус " + expectedStatusCode + ", а получен " + actualStatus +
                        ": " + response.getBody().asString());
            }

            return objectMapper.readValue(response.getBody().asString(), responseType);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при PATCH запросе на " + path, e);
        }
    }
}


/*package com.example.utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class HttpClientHelper {

    public static HttpResponse<JsonNode> postJson(String url, String body) {
        String fullUrl = Config.getBaseUrl() + url;
        return Unirest.post(fullUrl)
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();
    }

    public static HttpResponse<JsonNode> postJsonAuth(String url, String body, String token) {
        String fullUrl = Config.getBaseUrl() + url;
        return Unirest.post(fullUrl)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .asJson();
    }

    public static HttpResponse<JsonNode> getAuth(String url, String token) {
        String fullUrl = Config.getBaseUrl() + url;
        return Unirest.get(fullUrl)
                .header("Authorization", "Bearer " + token)
                .asJson();
    }

    public static HttpResponse<JsonNode> patchJsonAuth(String url, String body, String token) {
        String fullUrl = Config.getBaseUrl() + url;
        return Unirest.patch(fullUrl)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .asJson();
    }
}*/
