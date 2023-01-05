package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.EndPoints;

public class ApiApplicationRequest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getToken(int variant) {
        return ApiUtils.postRequest(String.format(EndPoints.GET_TOKEN, variant)).asString();
    }
}
