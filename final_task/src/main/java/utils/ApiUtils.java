package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiUtils {

    public static Response postRequest(String request) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when().post(request)
                .then()
                .extract().response();
    }
}
