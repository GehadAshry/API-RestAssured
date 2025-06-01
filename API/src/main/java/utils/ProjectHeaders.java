package utils;

import java.util.HashMap;

public class ProjectHeaders {


    public static HashMap<String, String> generalHeaders(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", LoginUtils.login(Constants.username,Constants.password).jsonPath().getString("token"));
        return headers;
    }

}
