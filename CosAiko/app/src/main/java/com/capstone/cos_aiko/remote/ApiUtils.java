package com.capstone.cos_aiko.remote;

/*
    This class sets the BASE_URL for the asynchronous REST Api calls
 */

public class ApiUtils {
    // url to RESTful API
    //public static final String BASE_URL = "http://152.13.82.65:8080/"; // uncg network ip
    public static final String BASE_URL = "http://192.168.56.1:8080/";
    //public static final String BASE_URL = "http://192.168.1.86:8080/"; //akasha

    /**
     * This function initializes an RetofitClient object with the BASE_URL
     *
     * @return the initialized RetrofitClient
     */
    public static UserService getUserService() {
        // create retrofit client
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}
