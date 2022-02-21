package com.capstone.cos_aiko.remote;

public class ApiUtils {
    // url to RESTful API
    public static final String BASE_URL = "http://152.13.76.150:8080/users/";

    public static UserService getUserService(){
        // create retrofit client
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}