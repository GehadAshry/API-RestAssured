package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginUtils {

    public static Response login (String username, String password){
        return RestAssured.given().when().contentType(ContentType.JSON).body("{\n" +

                "    \"email\": \""+ username +"\",\n" +

                "    \"password\": \""+ password +"\"\n" +

                "}").post(Constants.BaseURL+Constants.LoginEndPoint).then().extract().response();

    }

}
