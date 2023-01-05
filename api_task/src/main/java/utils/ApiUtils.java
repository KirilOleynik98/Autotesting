package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiUtils {

    public static Response getRequest(String response) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when().get(response)
                .then()
                .extract().response();
    }

    public static Response postRequest(String response, Object object) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(object)
                .when().post(response)
                .then()
                .extract().response();
    }
}
