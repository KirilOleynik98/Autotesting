package utils;

import parser.JsonParser;
import pojo.PostPojo;
import pojo.UserPojo;

import java.util.List;

public class ApiApplicationRequest {

    public List<PostPojo> getAllPosts() {
        return ApiUtils
                .getRequest(String.format(JsonParser.parseConfig().get("postResponse")))
                .then().statusCode(200).extract()
                .jsonPath().getList("", PostPojo.class);
    }

    public PostPojo getPost(int number) {
        return ApiUtils
                .getRequest(String.format(JsonParser.parseConfig().get("postResponse"), "/" + String.valueOf(number)))
                .then().statusCode(200).extract()
                .as(PostPojo.class);
    }

    public PostPojo postPost(PostPojo post) {
        return ApiUtils
                .postRequest(String.format(JsonParser.parseConfig().get("postResponse"), ""), post)
                .then().statusCode(201).extract()
                .as(PostPojo.class);
    }

    public List<UserPojo> getAllUsers() {
        return ApiUtils
                .getRequest(String.format(JsonParser.parseConfig().get("userResponse")))
                .then().statusCode(200).extract()
                .jsonPath().getList("", UserPojo.class);
    }

    public UserPojo getUser(int number) {
        return ApiUtils
                .getRequest(String.format(JsonParser.parseConfig().get("userResponse"), "/" + String.valueOf(number)))
                .then().statusCode(200).extract()
                .as(UserPojo.class);
    }

    public boolean postEquality(PostPojo firstPost, PostPojo secondPost) {
        return firstPost.getUserId() == secondPost.getUserId()
                & firstPost.getTitle().equals(secondPost.getTitle())
                & firstPost.getBody().equals(secondPost.getBody());
    }
}

