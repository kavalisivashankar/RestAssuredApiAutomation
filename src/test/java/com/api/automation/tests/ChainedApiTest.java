package com.api.automation.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ChainedApiTest {

    @Test
    public void chainApiCallsTest() {
        Response loginResponse = given()
            .contentType("application/json")
            .body("{\"username\":\"admin\",\"password\":\"password\"}")
        .when()
            .post("https://example.com/api/login")
        .then()
            .statusCode(200)
            .extract().response();

        String token = loginResponse.path("token");

        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("https://example.com/api/userinfo")
        .then()
            .statusCode(200)
            .body("username", equalTo("admin"));
    }
}
