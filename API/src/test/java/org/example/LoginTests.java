package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTests {

    static String username = "eve.holt@reqres.in";
    static String password = "cityslicka";
    static String login_URL = "https://reqres.in/api/login";
    static Response response;
    static int validStatus = 200;
    static int errorStatus = 400;
    SoftAssert softAssert = new SoftAssert();
    String token;

   // 1: helper method to facilitate login
    public void login(String username, String password) {
       response = RestAssured.given().when().contentType(ContentType.JSON).body("{\n" +

                "    \"email\": \""+ username +"\",\n" +

                "    \"password\": \""+ password +"\"\n" +

                "}").post(login_URL).then().extract().response();
    }

   //2: helper method to get the response whether it is token in valid cases or error in invalid cases
    public String response_body_content() {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.get("token") != null ? jsonPath.get("token") : jsonPath.get("error");
    }

    //3: helper method to get the status code
    public int response_status() {
        return response.getStatusCode();
    }


    @Test
    public void ValidLoginCredentials() {
        login(username, password);
        token = response_body_content();

        Assert.assertEquals(response_status(), validStatus,"unexpected response status");
        Assert.assertNotNull(token,"Token shouldn't be null");
        Assert.assertFalse(token.isEmpty(),"Token shouldn't be empty");
        //assuming that token is formed of mix of alphanumeric char and have minimum length
        Assert.assertTrue(token.matches("^[a-zA-Z0-9]+$"),"Invalid token");
        Assert.assertTrue(token.length() >= 15 ,"Invalid token");
    }

    @Test
    public void InvalidUsernameTest (){
        login("", password);

        Assert.assertEquals(response_status(), errorStatus);
        softAssert.assertNotNull(response_body_content(),"body is null");
        softAssert.assertEquals(response_body_content(), "Missing email or username","invalid error");
        softAssert.assertAll();

    }


    @Test
    public void InvalidPasswordTest (){
       login(username, "");

        Assert.assertEquals(response_status(), errorStatus);
        softAssert.assertNotNull(response_body_content(),"body is null");
        softAssert.assertEquals(response_body_content(), "Missing password","invalid error");
        softAssert.assertAll();

    }
}
