package com.yasin.slackchat;

/**
 * Created by im_yasinashraf started on 13/7/17.
 */

public class ApiUtils {

    private static final String BASE_URL = "https://slack.com/api/";

    public static ApiInterface getServices() {
        return RetrofitClient.getGCClient(BASE_URL).create(ApiInterface.class);
    }
}

