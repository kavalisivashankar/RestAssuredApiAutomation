package com.api.automation.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;

public class FileUploadTest {

    @Test
    public void uploadFileTest() {
        File file = new File("src/test/resources/testfile.txt");

        given()
            .multiPart("file", file)
        .when()
            .post("https://example.com/api/upload")
        .then()
            .statusCode(200)
            .body("message", equalTo("File uploaded successfully"));
    }
}
