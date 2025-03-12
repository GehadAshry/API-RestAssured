package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class UserTests {
    static String BaseURL = "https://reqres.in/api";
    static String UserEndPoint = "/users";
    static String LoginEndPoint = "/login";
    String token;
    Map<String,String> headers = new HashMap<>();
    static String username = "eve.holt@reqres.in";
    static String password = "cityslicka";
    SoftAssert softAssert = new SoftAssert();


    @BeforeClass
    public void Login(){
        Response response = RestAssured.given().when().contentType(ContentType.JSON).body("{\n" +

                "    \"email\": \""+ username +"\",\n" +

                "    \"password\": \""+ password +"\"\n" +

                "}").post(BaseURL+LoginEndPoint).then().extract().response();

                JsonPath jsonPath = response.jsonPath();
                token = jsonPath.get("token");

    }
    @Test
    public void getSingleUserTest() {
        String expectedId = "2";
        headers.put("Authorization", token);
        Response response = RestAssured.given().when().headers(headers).get (BaseURL+UserEndPoint+"/2").then().extract().response();
        JsonPath jsonPath = response.jsonPath();
        int actualId = jsonPath.get("data.id");
        Assert.assertEquals(response.statusCode(),200,"Status code is not correct");
        softAssert.assertEquals(String.valueOf(actualId),expectedId,"id is not correct");
        softAssert.assertNotNull(jsonPath.get("data.email"),"email is null");
        softAssert.assertNotNull(jsonPath.get("data.first_name"),"name is null");
        softAssert.assertAll();

    }
    @Test
    public void getListOfUsersTest() {
        String expectedTotalNumberOfUsers = "12";
        String expectedTotalPages = "2";

        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("page", "2");
        Response response = RestAssured.given().queryParams(queryParams).get (BaseURL+UserEndPoint).then().extract().response();
        JsonPath jsonPath = response.jsonPath();
        int actualPerPage = jsonPath.get("per_page");
        int actualTotalPages = jsonPath.get("total_pages");
        int actualTotalNumberOfUsers = jsonPath.get("total");
        String supportURL = jsonPath.get("support.url");

        Assert.assertEquals(response.statusCode(),200,"Status code is not correct");

        for (int i=0 ;i <jsonPath.getList("data").size(); i ++){
            softAssert.assertNotNull(jsonPath.get("data["+i+"].id"),"id is null");
            softAssert.assertNotNull(jsonPath.get("data["+i+"].email"),"email is null");
            softAssert.assertNotNull(jsonPath.get("data["+i+"].first_name"),"First name is null");
            softAssert.assertNotNull(jsonPath.get("data["+i+"].last_name"),"Last name is null");
            softAssert.assertNotNull(jsonPath.get("data["+i+"].avatar"),"Avatar is null");
        }
        softAssert.assertEquals(jsonPath.getList("data").size(),actualPerPage,"page is not correct");
        softAssert.assertEquals(String.valueOf(actualTotalPages),expectedTotalPages,"Number of Pages is not correct");
        softAssert.assertEquals(String.valueOf(actualTotalNumberOfUsers),expectedTotalNumberOfUsers,"Number of Users is not correct");
        softAssert.assertNotNull(supportURL,"support url is Null");
        softAssert.assertAll();

    }
    @Test
    public void CreateUserTest(){
        String expectedName = "Gehad";
        String expectedJob = "Tester";

        Response response = RestAssured.given().contentType(ContentType.JSON).body("{\n" +
                "    \"name\": \""+expectedName+"\",\n" +
                "    \"job\": \""+expectedJob+"\"\n" +
                "}").post(BaseURL+UserEndPoint).then().extract().response();

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.get("name");
        String actualJob = jsonPath.get("job");
        String responseID = jsonPath.get("id");
        String creationDate = jsonPath.get("createdAt");

        Assert.assertEquals(response.statusCode(),201,"Status code is not correct");
        softAssert.assertEquals(actualName,expectedName,"Name is not correct");
        softAssert.assertEquals(actualJob,expectedJob,"Job is not correct");
        softAssert.assertNotNull(responseID,"id is Null");
        softAssert.assertNotNull(creationDate,"creation Date is Null");
        softAssert.assertAll();

    }
}
