package com.example.bottomnavigationbar.webservice;

public class ApiUtils {
//    public static final String BASE_URL = "http://192.168.1.7/test/";
public static final String BASE_URL = "http://192.168.1.7:8000/api/";

    private ApiUtils() {
    }

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}