package utils;

import RequestModel.CreateUserRequestModel;
import ResponseModel.SingleUserResponseModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;


public class UserUtils {

    public static Response getSingleUser (int expectedId){
        return (Response) RestAssured.given().when().headers(ProjectHeaders.generalHeaders()).get (Constants.BaseURL+Constants.UserEndPoint+"/2").then().extract().as(SingleUserResponseModel.class);
    }

    public static Response getListUsers (String PageNo){
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("page", PageNo);
        return RestAssured.given().queryParams(queryParams).get (Constants.BaseURL+Constants.UserEndPoint).then().extract().response();
    }
    public static Response createUser (String name, String job){
        CreateUserRequestModel body = new CreateUserRequestModel();
        body.name = name;
        body.job = job;
        return RestAssured.given().contentType(ContentType.JSON).body(GeneralUtils.getObject(body)).post(Constants.BaseURL+Constants.UserEndPoint).then().extract().response();
    }
}
